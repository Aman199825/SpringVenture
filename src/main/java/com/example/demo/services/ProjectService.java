package com.example.demo.services;

import com.example.demo.domain.Project;
import com.example.demo.exception.ProjectIdException;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService
{
    @Autowired
    private ProjectRepository projectRepository;
    public Project saveOrUpdate(Project project)
    {

        try
        {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }
        catch(Exception e)
        {
            throw new ProjectIdException("Project Id: "+project.getProjectIdentifier().toUpperCase()+" already exists");
        }
        //return projectRepository.save(project);
    }
    public Project findProjectByIdentifier(String projectId)
    {
        return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    }
   /* public Iterable<Project> findAllProjects()
    {
        return projectRepository.findAllProjects();
    }*/
    public void deleteProject(String projectId)
    {
        Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
                        if(project==null)
                        {
                            throw new ProjectIdException("the project with Id: "+projectId+" doesn't exists");

                        }
        projectRepository.delete(project);

      }
}
