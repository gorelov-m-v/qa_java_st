package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.Set;

import static org.testng.Assert.*;
import static ru.stqa.pft.mantis.appmanager.SoapHelper.getMantisConnect;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();

        System.out.println(projects.size());
        for(Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue")
                                 .withDescription("Test issue description")
                                 .withProject(projects.iterator().next());

        Issue createdIssue = app.soap().addIssue(issue);

        assertEquals(issue.getSummary(), createdIssue.getSummary());
    }

    @Test
    public void smokeTest() throws MalformedURLException, ServiceException, RemoteException {

        // Допустим, у нас уже есть пользователь и проект с одним баг-репортом(с каким-нибудь статусом):
        Issue issue = app.soap().getIssues(app.soap().getProjects().iterator().next().getId()).iterator().next();
        int issueId = issue.getId();

        // Проверка статуса баг-репорта:
        skipIfNotFixed(issueId);

        // Какой-нибудь тест, если не пропускаем:
        assertTrue(app.soap().issueExists(issueId));
    }
}
