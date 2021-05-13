package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.Screenshot;

import java.util.concurrent.TimeUnit;

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void setUp(){
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\cursos\\automacao_api\\drivers\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Navegando para a página do Tasklist!
        navegador.get("http://juliodelima.com.br/taskit");

        // Clicar no link que possui texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com nome "login" que está dentro do formulário de id "signinbox" o texto "tielefernandes"
        formularioSignInBox.findElement(By.name("login")).sendKeys("tica");

        // Digitar no campo "password" que está dentro do formulário de id "signinbox" o texto "112469"
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");

        // Clicar no link com texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar em um link que possui a class 'me'
        navegador.findElement(By.className("me")).click();

        // Clicar no link que possui o texto 'MORE DATA ABOUT YOU'
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }

    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // Clicar no botão através de seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id addmoredata
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // No combo de name "type" escolher opção "value = phone"/ "índice = [1]" / "texto = Phone"
        popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText("Phone");

        // No campo "contact" digitar "+5547991234567"
        popupAddMoreData.findElement(By.name("contact")).sendKeys("+5547991234567");

        // Clicar no link de texto "SAVE" que está na popup
        popupAddMoreData.findElement(By.linkText("SAVE"));

        // Na mensagem de id "toast-container" validar que o text é 'Your contact has been added!'
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Your contact has been added!", mensagem);
    }

    @Test
    public void removerUmContatoDeUmUsuario(){
        // Clicar no elemento pelo seu xpath //span[text() = "+5547991234567"]/following-sibling::a
        navegador.findElement(By.xpath("xpath //span[text() = \"+5547991234567\"]/following-sibling::a"));

        // Confirmar exclusão em janela alert javascript
        navegador.switchTo().alert().accept();

        // Validar que a mensagem apresentada em tela foi: 'Rest in peace, dear phone!'
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "C:\\Users\\tiele.fernandes\\IdeaProjects\\webdriver-java\\src\\test\\java\\taskit"
                + Generator.dataHoraParaArquivo() + test.getMethodName() +".png";
        Screenshot.tirar(navegador, screenshotArquivo);

        // Aguardar até 10 segundos para que a janela desapareça utilizando espera explícita
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

        // Clicar no link com texto "Logout"
        navegador.findElement(By.linkText("Logout")).click();
    }

    @After
    public void tearDown(){
        // Fechar o navegador
        //navegador.quit();
    }
}
