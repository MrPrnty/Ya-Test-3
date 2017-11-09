/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ya_test_3;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author Александр
 */

public class Ya_Test_3 {

    /**
     * @param args the command line arguments
     */  
    private static WebDriver driver = null;
    
    public static void main(String[] args) {
        // TODO code application logic here
        System.setProperty("webdriver.chrome.driver", "E:\\Downloads\\chromedriver_win32\\chromedriver.exe"); 
        driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();  
        driver.get("https://yandex.ru");        
        driver.findElement(By.linkText("Маркет")).click();       
        driver.findElement(By.linkText("Электроника")).click(); 
        driver.findElement(By.linkText("Мобильные телефоны")).click();
        WebElement FirstCell = driver.findElement(By.cssSelector("div:nth-child(1) > div.n-snippet-cell2__body > div.n-snippet-cell2__price > div > a > div")); 
        String FirstPrice = FirstCell.getText();
        driver.findElement(By.linkText("по цене")).click(); 
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(FirstCell, FirstPrice)));
        List<WebElement> Price_ar = driver.findElements(By.cssSelector("div.n-snippet-cell2__body > div.n-snippet-cell2__price > div > a > div"));
        boolean PriceFlag = true;
        int prevPrice = Integer.parseInt(Price_ar.get(0).getText().replaceAll("\\D",""));
        int curPrice; 
        for (int i = 1; i < Price_ar.size(); i++) {
            curPrice=Integer.parseInt(Price_ar.get(i).getText().replaceAll("\\D",""));
            if (curPrice < prevPrice)    
            {
                PriceFlag=false;
                System.out.println(curPrice+" < "+prevPrice);  
                break;
            }
            else
                prevPrice = Integer.parseInt(Price_ar.get(i).getText().replaceAll("\\D",""));   
        } 
        if (PriceFlag)
            System.out.println("Элементы на странице отсортированы верно!");       
        else
            System.out.println("Элементы на странице отсортированы неверно!");
        driver.close();
    }
    
}
