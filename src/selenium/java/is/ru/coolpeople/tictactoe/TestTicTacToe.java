package is.ru.coolpeople.tictactoe;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestTicTacToe extends SeleniumTestWrapper {
  @Test
  public void testTitleMatches() {
    driver.get(baseUrl);
    assertEquals("Tic Tac Toe", driver.getTitle());
  }

  @Test
  public void testPlayerOneName() throws Exception {
    driver.get(baseUrl);
    /* Remove Thread.sleep... */
    WebElement input = driver.findElement(By.id("pcOneName"));
    input.sendKeys("Adam");
    assertEquals("Adam", input.getAttribute("value"));
  }

  @Test
  public void testPlayerCountAndClick() throws Exception {
    driver.get(baseUrl);
    /* Remove Thread.sleep... */
    WebElement input = driver.findElement(By.xpath("//input[@name='playerCount']"));
    assertEquals("1", input.getAttribute("value"));
    driver.findElement(By.id("pcTwo")).click();
    assertEquals("2 ", input.getAttribute("value"));

    WebElement psTwoDiv = driver.findElement(By.className("psTwo"));
    assertTrue(psTwoDiv.getAttribute("class").contains("show"));
  }

  @Test
  public void testTwoPlayerGame() throws Exception {
    driver.get(baseUrl);
    /* Remove Thread.sleep... */
    driver.findElement(By.id("pcTwo")).click();
    WebElement input = driver.findElement(By.xpath("//input[@name='playerCount']"));
    assertEquals("2 ", input.getAttribute("value"));


    WebElement input2 = driver.findElement(By.id("pcOneName"));
    input2.sendKeys("Adam");
    assertEquals("Adam", input2.getAttribute("value"));

    WebElement input3 = driver.findElement(By.id("pcTwoName"));
    input3.sendKeys("Bob");
    assertEquals("Bob", input3.getAttribute("value"));

    driver.findElement(By.className("startGame")).click();

    WebElement input4 = driver.findElement(By.id("menuWrapper"));
    assertFalse(input4.getAttribute("class").contains("show"));


    List<WebElement> gameTiles =  driver.findElements(By.className("gameTile"));
    // click the 3rd checkbox
    gameTiles.get(0).click();
    gameTiles.get(3).click();
    gameTiles.get(1).click();
    gameTiles.get(4).click();
    gameTiles.get(2).click();

    WebElement input5 = driver.findElement(By.id("gameOver"));
    assertTrue(input5.getAttribute("class").contains("show"));

  }

}