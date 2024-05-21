package com.apifilmeseries.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifilmeseries.dao.FilmeiSeriesDAO;
import com.apifilmeseries.dao.JogoDAO;
import com.apifilmeseries.dao.ListaJogosDAO;
import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.FilmeSerieDTO;
import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;
import com.apifilmeseries.service.FavoritoService;
import com.apifilmeseries.service.FilmeSerieService;
import com.apifilmeseries.service.GameService;
import com.apifilmeseries.service.QRCodeService;
import com.apifilmeseries.service.UtilService;
import com.google.zxing.WriterException;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private GameService gameService;

    @Autowired
    private FilmeSerieService filmeSerieService;  
    
    @Autowired
    private ListaJogosDAO listaGamesService;  
    
    @Autowired
    private FilmeiSeriesDAO filmeiSeriesDAO;
    
    @Autowired
    private JogoDAO jogoDAO;
    
    
    @Autowired
    private UtilService utilService;

    @GetMapping("/game/{id}")
    public ResponseEntity<byte[]> generateGameQRCode(@PathVariable Long id) throws WriterException, IOException {
        GameDTO gameDTO = gameService.buscarJogoDTOPorId(id);
        byte[] qrCodeImage = qrCodeService.generateQRCode(gameDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }

    @GetMapping("/filmeserie/{id}")
    public ResponseEntity<byte[]> generateFilmeSerieQRCode(@PathVariable Long id) throws WriterException, IOException {
        FilmeSerieDTO filmeSerieDTO = filmeSerieService.buscarFilmeSerieDTOPorId(id);
        byte[] qrCodeImage = qrCodeService.generateQRCode(filmeSerieDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }

    @GetMapping("/listagames/{id}/tipo/listaGame/{tipo}")
    public ResponseEntity<byte[]> generateListaGamesQRCode(@PathVariable Long id, @PathVariable int tipo) throws WriterException, IOException {
       List<ListaGamesDTO> listaGamesDTO = listaGamesService.consultarListaJogosPorUsuarioEType(id, utilService.tipoListaGame(tipo));
        byte[] qrCodeImage = qrCodeService.generateQRCode(listaGamesDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }

    @GetMapping("/favoritoconsulta/{id}")
    public ResponseEntity<byte[]> generateFavoritoConsultaQRCode(@PathVariable Long id) throws WriterException, IOException {
        List<FavoritoConsultaDTO> favoritoConsultaDTOs = filmeiSeriesDAO.consultarFavoritos(id);
        
        if (favoritoConsultaDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        byte[] qrCodeImage = qrCodeService.generateQRCodes(favoritoConsultaDTOs);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }
    
    @GetMapping("/favoritogame/{id}")
    public ResponseEntity<byte[]> generateFavoritoGameQRCode(@PathVariable Long id) throws WriterException, IOException {
        List<FavoritoGameDTO> favoritoGameDTOs = jogoDAO.consultarFavoritosGame(id); 
        
        if (favoritoGameDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        byte[] qrCodeImage = qrCodeService.generateQRCodess(favoritoGameDTOs);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok().headers(headers).body(qrCodeImage);
    }

}
