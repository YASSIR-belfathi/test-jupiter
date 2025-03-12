package com.junitTest.junit_jupiter_test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JunitJupiterTestApplicationTests {
	// question 1
	private SendNotification sendNotification;
	private CompteBancaire compteBancaire;
	private CompteBancaire compteBancaire2;
	private SendNotification sendNotification2;

	@BeforeEach
	public void Setup() {
		sendNotification2 = mock(SendNotification.class);
		sendNotification = mock(SendNotification.class);
		compteBancaire = new CompteBancaire(1000, sendNotification);
		compteBancaire2 = new CompteBancaire(50, sendNotification2);
	}

	// question 2
	@Test
	public void TestDeposerCompte() {
		compteBancaire.deposer(50.0);
		assertEquals(1050.0, compteBancaire.getSolde(), "le solde a été bien déposer");
		verify(sendNotification).sendNotification("Dépôt de 50.0 effectué.");
	}

	// question 3
	@Test
	public void RetraitCompte() {
		assertThrows(IllegalArgumentException.class, () -> compteBancaire.retirer(1060));
		verify(sendNotification, never()).sendNotification(anyString());
	}

	// question 4
	@Test
	public void DeuxDepot() {
		compteBancaire.deposer(1000);
		compteBancaire.deposer(50);
		verify(sendNotification, times(2)).sendNotification(anyString());
	}

	// question 5
	@Test
	public void ComplexMockInteraction() {
		assertThrows(IllegalArgumentException.class, () -> compteBancaire.transfersVers(compteBancaire2, 100));
		verify(sendNotification).sendNotification(anyString());
		verify(sendNotification2).sendNotification(anyString());
	}
}
