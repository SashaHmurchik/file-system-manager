package com.epam.project.web.controller;


import com.epam.project.entity.PathTreeModel;
import com.epam.project.manager.exception.FileOrCatalogAlreadyExistException;
import com.epam.project.service.FileSystemService;
import com.epam.project.web.converter.UriConverter;
import com.epam.project.web.dto.MessageResponseDto;
import com.epam.project.web.dto.PathDto;
import com.epam.project.web.handler.BindingResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.file.Paths;


@Controller
@RestController
@RequestMapping(value = "/file")
@CrossOrigin(origins = "*")
public class FileSystemController {

    @Autowired
    private FileSystemService fileSystemService;


    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/**")
    @CrossOrigin(origins = "*")
    public PathTreeModel findByPath(HttpServletRequest request) {
        return fileSystemService.getPathTree(UriConverter.convertUriToPath(request.getRequestURI(), "/file/"));
    }

    @PostMapping(value = "/directory")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createCatalog(
//            @Valid
            @RequestBody
                    PathDto pathDto, BindingResult bindingResult) {
        MessageResponseDto messageResponseDto = BindingResultHandler.validEntity(bindingResult);

        if (null != messageResponseDto) {
            return new ResponseEntity<MessageResponseDto>(
                    messageResponseDto,
                    HttpStatus.valueOf(messageResponseDto.getStatusCode()));
        }

        return new ResponseEntity<PathTreeModel>(
                        fileSystemService.createCatalog(Paths.get(pathDto.getPath()),
                        pathDto.getName()), HttpStatus.OK);
    }

    @PostMapping(value = "/file")
    @CrossOrigin(origins = "*")
    public ResponseEntity<?> createFile (
            @Valid
            @RequestBody
                    PathDto pathDto, BindingResult bindingResult) {
        MessageResponseDto messageResponseDto = BindingResultHandler.validEntity(bindingResult);

        if (null != messageResponseDto) {
            return new ResponseEntity<MessageResponseDto>(
                    messageResponseDto,
                    HttpStatus.valueOf(messageResponseDto.getStatusCode()));
        }

        return new ResponseEntity<PathTreeModel>(
                fileSystemService.createFile(Paths.get(pathDto.getPath()),
                        pathDto.getName()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/**")
    @CrossOrigin(origins = "*")
    public ResponseEntity<PathTreeModel> deleteCatalogOrFile(HttpServletRequest request) {
        return new ResponseEntity<>(fileSystemService
                        .deleteFileOrCatalog(UriConverter
                        .convertUriToPath(request.getRequestURI(), "/file/")),
                        HttpStatus.OK);
    }

}
