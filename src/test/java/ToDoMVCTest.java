import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

/**
 * Created by Андрей on 07.12.2015.
 */
public class ToDoMVCTest{

    @Test
    public void testTaskCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        addTasks("1", "2", "3", "4");
        assertTasks("1", "2", "3", "4");


        deleteTask("2");
        assertTasks("1", "3", "4");

        toggleTask("4");
        clearComplitedTasks();
        assertTasks("1", "3");

        toggleAllTasks();
        clearComplitedTasks();
        taskList.shouldBe(empty);
    }

    ElementsCollection taskList = $$("#todo-list li");

    private void addTasks(String... tasks) {

        for(String addTask :tasks) {
            $("#new-todo").setValue(addTask).pressEnter();
        }
    }

    private void assertTasks(String... tasks) {

        taskList.shouldHave(exactTexts(tasks));

    }

    public void toggleAllTasks() {
        $("#toggle-all").click();
    }

    public void clearComplitedTasks() {
        $("#clear-completed").click();
    }

    public void toggleTask(String task) {
        taskList.findBy(exactText(task)).find(".toggle").click();

    }

    public void deleteTask(String task) {
        taskList.findBy(exactText(task)).hover().$(".destroy").click();
    }

}




