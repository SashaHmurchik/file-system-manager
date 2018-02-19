package com.epam.project.manager;


import com.epam.project.entity.PathTreeModel;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;


import java.nio.file.Path;

public interface FileSystemManager {
    /**
     * Creates Path Tree Structure with files and catalogs structure
     * @param dir
     *           is the root dir
     * @return PathTreeModel
     */
    PathTreeModel getPathTree(Path path);

    /**
     * Delete file or folder by given absolute Path
     *
     * @param path
     *            is the absolute path to file
     * @return the Path Tree Structure of parent's directory
     */
    PathTreeModel deleteFileOrCatalog(Path path);

    /**
     * Creates file by given name and path
     *
     * @param name
     *            is the name of new file
     * @param path
     *            is the absolute path to parent catalog
     * @return the FileStructure of parent's directory
     * @throws FileOrCatalogAlreadyExistException
     **/
    PathTreeModel createFile(Path path, String name) throws FileOrCatalogAlreadyExistException;

    /**
     * Creates catalog by given name and path
     *
     * @param name
     *            is the name of new catalog
     * @param path
     *            is the absolute path to parent catalog
     * @return the FileStructure of parent's directory
     * @throws FileOrCatalogAlreadyExistException
     **/
    PathTreeModel createCatalog(Path path, String name) throws FileOrCatalogAlreadyExistException;
}
