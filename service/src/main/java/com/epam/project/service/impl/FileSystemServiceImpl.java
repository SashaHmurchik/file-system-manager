package com.epam.project.service.impl;


import com.epam.project.entity.PathTreeModel;
import com.epam.project.manager.FileSystemManager;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;
import com.epam.project.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.*;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Autowired
    private FileSystemManager fileSystemManager;

    @Override
    public PathTreeModel getPathTree(Path dir) {
        return fileSystemManager.getPathTree(dir);
    }

    @Override
    public PathTreeModel deleteFileOrCatalog(Path path) {
        return fileSystemManager.deleteFileOrCatalog(path);
    }

    @Override
    public PathTreeModel createFile(Path path, String name) {
        return fileSystemManager.createFile(path, name);
    }

    @Override
    public PathTreeModel createCatalog(Path path, String name) {
        return fileSystemManager.createCatalog(path, name);
    }
}
