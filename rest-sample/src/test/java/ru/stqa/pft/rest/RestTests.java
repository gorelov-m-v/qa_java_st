package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(9);

        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue().withSubject("test issue").withDescription("test description");

        int issueId = createIssue(newIssue);

        Set<Issue> newIssues = getIssues();
        oldIssues.add(newIssue.withId(issueId).withSubject(newIssue.getSubject()).withDescription(newIssue.getDescription()));

        assertEquals(newIssues, oldIssues);
    }
}
