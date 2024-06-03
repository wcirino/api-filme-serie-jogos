package com.apifilmeseries.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.FilmeSerieDTO;
import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;
import com.google.zxing.WriterException;

class QRCodeServiceTest {

	@Test
	void testGenerateQRCodeFromGameDTO() {
		GameDTO gameDTO = new GameDTO();
		gameDTO.setIdJogo(1L);
		gameDTO.setTitulo("Super Mario");
		

		QRCodeService qrCodeService = new QRCodeService();
		try {
			byte[] qrCodeBytes = qrCodeService.generateQRCode(gameDTO);
			assertNotNull(qrCodeBytes);
			
		} catch (WriterException | IOException e) {
			fail("Exceção inesperada: " + e.getMessage());
		}
	}

	@Test
	void testGenerateQRCodeFromFilmeSerieDTO() {
		FilmeSerieDTO filmeSerieDTO = new FilmeSerieDTO();
		filmeSerieDTO.setId(1L);
		filmeSerieDTO.setTitulo("Pulp Fiction");
		
		QRCodeService qrCodeService = new QRCodeService();
		try {
			byte[] qrCodeBytes = qrCodeService.generateQRCode(filmeSerieDTO);
			assertNotNull(qrCodeBytes);
			
		} catch (WriterException | IOException e) {
			fail("Exceção inesperada: " + e.getMessage());
		}
	}

	@Test
	void testGenerateQRCodeFromListaGamesDTOList() {
		List<ListaGamesDTO> listaGamesDTO = new ArrayList<>();
		
		ListaGamesDTO dto = new ListaGamesDTO();
		dto.setId(1L);
		dto.setClassificacao(2);
		
		listaGamesDTO.add(dto);

		QRCodeService qrCodeService = new QRCodeService();
		try {
			byte[] qrCodeBytes = qrCodeService.generateQRCode(listaGamesDTO);
			assertNotNull(qrCodeBytes);
			
		} catch (WriterException | IOException e) {
			fail("Exceção inesperada: " + e.getMessage());
		}
	}

	@Test
	void testGenerateQRCodesFromFavoritoConsultaDTOList() {
		List<FavoritoConsultaDTO> favoritos = new ArrayList<>();
		
		FavoritoConsultaDTO favorito = new FavoritoConsultaDTO();
		favorito.setNumMatricula(12345);
		
		favoritos.add(favorito);

		QRCodeService qrCodeService = new QRCodeService();
		try {
			byte[] qrCodeBytes = qrCodeService.generateQRCodes(favoritos);
			assertNotNull(qrCodeBytes);

		} catch (WriterException | IOException e) {
			fail("Exceção inesperada: " + e.getMessage());
		}
	}

	@Test
	void testGenerateQRCodesFromFavoritoGameDTOList() {
		List<FavoritoGameDTO> favoritos = new ArrayList<>();
		
		FavoritoGameDTO favorito = new FavoritoGameDTO();
		favorito.setId(1L);
		favorito.setTitulo("The Legend of Zelda");
		
		favoritos.add(favorito);

		QRCodeService qrCodeService = new QRCodeService();
		try {
			byte[] qrCodeBytes = qrCodeService.generateQRCodess(favoritos);
			assertNotNull(qrCodeBytes);
	
		} catch (WriterException | IOException e) {
			fail("Exceção inesperada: " + e.getMessage());
		}
	}
}
