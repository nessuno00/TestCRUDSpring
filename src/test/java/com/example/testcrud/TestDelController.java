package com.example.testcrud;

import Controller.StudentCRUD;
import Service.StudentRepository;
import Service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(StudentCRUD.class)
@AutoConfigureMockMvc
public class TestDelController {

    @Autowired

    private MockMvc mockMvc;

    @MockBean

    private StudentService studentService;


    @MockBean
    StudentRepository studentRepository;


    @Test

    public void testGetAllStudents() throws Exception {


        when(studentRepository.findAll()).thenReturn(Arrays.asList(new Student(), new Student()));


        mockMvc.perform(MockMvcRequestBuilders.get("/students"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.length()").value(2));

    }

    @Test
    void getStudentById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/students/{id}", 1))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id").exists())
                .andExpect((ResultMatcher) jsonPath("$.name").exists())
                .andExpect((ResultMatcher) jsonPath("$.surname").exists())
                .andExpect((ResultMatcher) jsonPath("$.isWorking").exists());
    }

}