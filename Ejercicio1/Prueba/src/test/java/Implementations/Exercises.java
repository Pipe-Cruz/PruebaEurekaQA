package Implementations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Exercises {
	
	WebDriver driver;
	String browser = "chrome";
	
	@Given("^user open Chrome browser$")
	public void user_search_BC_on_Chrome() {
		driver = utilities.DriverFactory.open(browser);
	}
	
	@When("^user search main page of Banco Central$")
	public void user_in_on_main_page_BC() {
		driver.get("http://www.bcentral.cl");
		System.out.println("User in on main page of Banco Central");
	}
	
	@And("^number of h1 tags is validated$")
	public void number_h1_tags_validated() {
		List<WebElement> h1Elements = driver.findElements(By.tagName("h1"));
        System.out.println("Cantidad de etiquetas H1: "+String.valueOf(h1Elements.size()));
        for (WebElement element : h1Elements) {
            System.out.println("Texto en etiqueta H1: " + element.getText());
        }
	}
	
	@And("^number of p tags is validated$")
	public void number_p_tags_validated() {
    	List<WebElement> pElements = driver.findElements(By.tagName("p"));
    	System.out.println("Cantidad de etiquetas de párrafo (p): "+String.valueOf(pElements.size()));
	}
	
	@And("^site title is validated$")
	public void site_title_validated() {
    	String title = driver.getTitle();;
        if (title.equals("Inicio - Banco Central de Chile")) {
            System.out.println("Título correcto!!");
        } else {
            System.out.println("Error en el título.");
        }    
	}
	
	@Then("^user can view the value of the coins$")
	public void user_view_value_coins() {
    	String UF = driver.findElement(By.xpath("//*[@id=\"_BcentralIndicadoresViewer_INSTANCE_pLcePZ0Eybi8_myTooltipDelegate\"]/div/div/div[1]/div/div/div[1]/div/p[2]")).getText();
    	String UTM = driver.findElement(By.xpath("//*[@id=\"_BcentralIndicadoresViewer_INSTANCE_pLcePZ0Eybi8_myTooltipDelegate\"]/div/div/div[1]/div/div/div[2]/div/p[2]")).getText();
    	String USD = driver.findElement(By.xpath("//*[@id=\"_BcentralIndicadoresViewer_INSTANCE_pLcePZ0Eybi8_myTooltipDelegate\"]/div/div/div[1]/div/div/div[3]/div/p[2]")).getText();
    	String EUR = driver.findElement(By.xpath("//*[@id=\"_BcentralIndicadoresViewer_INSTANCE_pLcePZ0Eybi8_myTooltipDelegate\"]/div/div/div[1]/div/div/div[4]/div/p[2]")).getText();
    	System.out.println("Valor UF: "+UF+"\nValor UTM: "+UTM+"\nValor Dólar Observado: "+USD+"\nValor Euro: "+EUR);
    }
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
}
