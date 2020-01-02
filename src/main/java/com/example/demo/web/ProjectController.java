package com.example.demo.web;

import com.example.demo.services.MapErrorService;
import com.example.demo.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.example.demo.domain.Project;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/project")
public class ProjectController
{
 @Autowired
    private ProjectService projectService;
 @Autowired
    MapErrorService errorService;
    @PostMapping("/post")
        public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result)
    {
          ResponseEntity<?> errorMap=errorService.mapErrorService(result);
          if(errorMap!=null)
          {
              return errorMap;
          }
          Project project1=projectService.saveOrUpdate(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<?> findByProjectIdentifier(@PathVariable String projectId)
    {
        Project project=projectService.findProjectByIdentifier(projectId);
        return  new ResponseEntity<>(project,HttpStatus.ACCEPTED);
    }
    /*@GetMapping("All")
    public Iterable<Project> findAllProjects()
    {
        return projectService.findAllProjects();
    }*/
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId)
    {
           projectService.deleteProject(projectId);
           return new ResponseEntity<String>("Project having id: "+projectId+" was deleted sucessfully. ",HttpStatus.ACCEPTED);
    }
}
