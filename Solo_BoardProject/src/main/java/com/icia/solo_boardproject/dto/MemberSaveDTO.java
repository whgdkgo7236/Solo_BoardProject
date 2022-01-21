package com.icia.solo_boardproject.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSaveDTO {
    private String origFilename;
    private String m_filename;
    private MultipartFile m_file;
    private String filePath;
    private String m_email;
    private String m_password;
    private String m_name;
    private String m_phonenum;



}
