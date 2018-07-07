package com.textanalizer.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private List<String> wordsCountList = new ArrayList<>();
    private boolean isBracketsOk;
}