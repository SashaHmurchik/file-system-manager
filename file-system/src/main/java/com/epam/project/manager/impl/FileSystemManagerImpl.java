package com.epam.project.manager.impl;

import com.epam.project.entity.PathModel;
import com.epam.project.entity.PathTreeModel;
import com.epam.project.manager.FileSystemManager;
import com.epam.project.manager.exception.CatalogIsNotExistException;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class FileSystemManagerImpl works with file system of environment it's
 * hosted on.
 */
@Service
public class FileSystemManagerImpl implements FileSystemManager {

    private static final Path MAIN_ROOT = Paths.get("D://ecl//ROOT");

    @Override
    public PathTreeModel getPathTree(Path dir) {
        isExistPath(dir);
            if (dir == null || dir.toString().isEmpty()) {
                dir = MAIN_ROOT;
            }
            PathTreeModel pathTreeModel = new PathTreeModel();
            pathTreeModel.setCurrentDir(dir.toString());
            if (dir.getParent() != null) {
                pathTreeModel.setPreviousDir(dir.getParent().toString());
            }

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                Set<PathModel> pathModels = new HashSet<>();
                for (Path path : stream) {
                    PathModel pathModel = new PathModel(path.toString(), Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS));
                    pathModels.add(pathModel);
                }
                pathTreeModel.setChildrens(pathModels);
            } catch (IOException | DirectoryIteratorException x) {
                // LOGGER must be HERE
                throw new RuntimeException(x);
            }
        return pathTreeModel;
    }

    @Override
    public PathTreeModel deleteFileOrCatalog(Path path) {
        if (isExistPath(path)){
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                //LOGGER must be  HERE
                return null;
            }
        }
        return getPathTree(path.getParent());
    }

    @Override
    public PathTreeModel createFile(Path path, String name) {
        if(isExistPath(path)) {
            try {
                Files.createFile(path.resolve(name));
            } catch (IOException e) {
                //LOGGER must be HERE
                throw new FileOrCatalogAlreadyExistException("File already exist", e);
            }
        }
        return getPathTree(path);
    }

    @Override
    public PathTreeModel createCatalog(Path path, String name) {
        if(isExistPath(path)) {
            try {
                Files.createDirectory(path.resolve(name));
            } catch (IOException e) {
                //LOGGER must be HERE
                throw new FileOrCatalogAlreadyExistException("Catalog already exist", e);
            }
        }
        return getPathTree(path);
    }

    private boolean isExistPath(Path path) {
        if (Files.notExists(path)) {
            throw new CatalogIsNotExistException("Catalog '" + path + "' is not exist in file system");
        }
        return true;
    }
}
