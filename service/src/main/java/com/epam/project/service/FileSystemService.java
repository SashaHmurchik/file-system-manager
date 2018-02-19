package com.epam.project.service;


import com.epam.project.entity.PathTreeModel;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;

import java.nio.file.Path;

public interface FileSystemService {
    PathTreeModel getPathTree(Path dir);
    PathTreeModel deleteFileOrCatalog(Path path);
    PathTreeModel createFile(Path path, String name) throws FileOrCatalogAlreadyExistException;
    PathTreeModel createCatalog(Path path, String name) throws FileOrCatalogAlreadyExistException;
}
