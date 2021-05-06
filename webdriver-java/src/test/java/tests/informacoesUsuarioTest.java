package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class informacoesUsuarioTest {
    @Test
    public void testAdicionarUmaInformacaoAdicionalDoUsuario() {
        // Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\cursos\\automacao_api\\drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        // Navegando para a página do Tasklist!
        navegador.get("http://juliodelima.com.br/taskit");

        // Clicar no link que possui texto "Sign in"
        navegador.findElement(By.linkText("Sign in")).click();

        // Identificando o formulário de Login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));

        // Digitar no campo com nome "login" que está dentro do formulário de id "signinbox" o texto "tielefernandes"
        formularioSignInBox.findElement(By.name("login")).sendKeys(  "tica");

        // Digitar no campo "password" que está dentro do formulário de id "signinbox" o texto "112469"
        formularioSignInBox.findElement(By.name("password")).sendKeys( "123456");

        // Clicar no link com texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Validar que dentro do elemento com class "me" está o texto "Hi, Tiele"

        // Fechar o navegador
    }
}
