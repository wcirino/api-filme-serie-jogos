package com.apifilmeseries.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.apifilmeseries.dto.FavoritoConsultaDTO;
import com.apifilmeseries.dto.FavoritoGameDTO;
import com.apifilmeseries.dto.FilmeSerieDTO;
import com.apifilmeseries.dto.GameDTO;
import com.apifilmeseries.dto.ListaGamesDTO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

@Service
public class QRCodeService {

    public byte[] generateQRCode(GameDTO gameDTO) throws WriterException, IOException {
        String qrContent = buildQRCodeContent(gameDTO);
        return generateQRCodeImage(qrContent);
    }

    public byte[] generateQRCode(FilmeSerieDTO filmeSerieDTO) throws WriterException, IOException {
        String qrContent = buildQRCodeContent(filmeSerieDTO);
        return generateQRCodeImage(qrContent);
    }

    public byte[] generateQRCode(List<ListaGamesDTO> listaGamesDTO) throws WriterException, IOException {
        StringBuilder qrContentBuilder = new StringBuilder();
        for (ListaGamesDTO dto : listaGamesDTO) {
            String qrContent = buildQRCodeContent(dto);
            qrContentBuilder.append(qrContent).append("\n"); 
        }
        String qrContent = qrContentBuilder.toString();
        return generateQRCodeImage(qrContent);
    }


    public byte[] generateQRCodes(List<FavoritoConsultaDTO> favoritos) throws WriterException, IOException {

        StringBuilder qrContentBuilder = new StringBuilder();
        for (FavoritoConsultaDTO favorito : favoritos) {

            qrContentBuilder.append(buildQRCodeContent(favorito));
            qrContentBuilder.append("\n"); 
        }
       
        String qrContent = qrContentBuilder.toString();
        return generateQRCodeImage(qrContent);
    }


    public byte[] generateQRCodess(List<FavoritoGameDTO> favoritos) throws WriterException, IOException {
        StringBuilder qrContentBuilder = new StringBuilder();
        for (FavoritoGameDTO favorito : favoritos) {
            qrContentBuilder.append(buildQRCodeContent(favorito));
            qrContentBuilder.append("\n");
        }

        String qrContent = qrContentBuilder.toString();
        return generateQRCodeImage(qrContent);
    }


    private byte[] generateQRCodeImage(String qrContent) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 300;
        int height = 300;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = qrCodeWriter.encode(qrContent, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }
    
    private String buildQRCodeContent(GameDTO gameDTO) {
        return "ID: " + gameDTO.getIdJogo() + "\n" +
                "Título: " + gameDTO.getTitulo() + "\n" +
                "Descrição: " + gameDTO.getDescricao() + "\n" +
                "Gênero: " + gameDTO.getGenero() + "\n" +
                "Ano de Lançamento: " + gameDTO.getAnoLancamento() + "\n" +
                "Plataforma: " + gameDTO.getPlataforma() + "\n" +
                "Estúdio de Produção: " + gameDTO.getEstudioProducao() + "\n" +
                "Classificação: " + gameDTO.getClassificacao() + "\n" +
                "URL da Imagem do Jogo: " + gameDTO.getImagemUrlJogo() + "\n" +
                "URL da Imagem da Plataforma: " + gameDTO.getImagemUrlPlataforma() + "\n" +
                "Console: " + gameDTO.getConsole();
    }

    private String buildQRCodeContent(FilmeSerieDTO filmeSerieDTO) {
        return "ID: " + filmeSerieDTO.getId() + "\n" +
                "Título: " + filmeSerieDTO.getTitulo() + "\n" +
                "Sinopse: " + filmeSerieDTO.getSinopse() + "\n" +
                "Gênero: " + filmeSerieDTO.getGenero() + "\n" +
                "Ano de Lançamento: " + filmeSerieDTO.getAnoLancamento() + "\n" +
                "Elenco: " + filmeSerieDTO.getElenco() + "\n" +
                "Classificação: " + filmeSerieDTO.getClassificacao() + "\n" +
                "Diretor: " + filmeSerieDTO.getDiretor() + "\n" +
                "Duração: " + filmeSerieDTO.getDuracao() + " min\n" +
                "URL da Imagem: " + filmeSerieDTO.getImagemUrl() + "\n" +
                "Plataforma: " + filmeSerieDTO.getPlataforma() + "\n" +
                "Tipo: " + filmeSerieDTO.getTipo();
    }

    private String buildQRCodeContent(ListaGamesDTO listaGamesDTO) {
        return "ID: " + listaGamesDTO.getId() + "\n" +
                "Classificação: " + listaGamesDTO.getClassificacao() + "\n" +
                "Comentário: " + listaGamesDTO.getComentario() + "\n" +
                "Data da Ação: " + listaGamesDTO.getDataAcao() + "\n" +
                "ID do Jogo: " + listaGamesDTO.getIdJogo() + "\n" +
                "ID do Usuário: " + listaGamesDTO.getIdUsuario() + "\n" +
                "Tipo de Ação: " + listaGamesDTO.getTipoAcao() + "\n" +
                "Título do Jogo: " + listaGamesDTO.getTituloJogo() + "\n" +
                "Plataforma: " + listaGamesDTO.getPlataforma() + "\n" +
                "Console: " + listaGamesDTO.getConsole() + "\n" +
                "Estúdio de Produção: " + listaGamesDTO.getEstudioProducao() + "\n" +
                "Nome do Usuário: " + listaGamesDTO.getNomeUsuario();
    }

    private String buildQRCodeContent(FavoritoConsultaDTO favoritoConsultaDTO) {
        return "Matrícula: " + favoritoConsultaDTO.getNumMatricula() + "\n" +
                "ID do Filme/Série: " + favoritoConsultaDTO.getIdFilmeSerie() + "\n" +
                "Nome do Usuário: " + favoritoConsultaDTO.getNomeUsuario() + "\n" +
                "Título: " + favoritoConsultaDTO.getTitulo() + "\n" +
                "Sinopse: " + favoritoConsultaDTO.getSinopse() + "\n" +
                "Gênero: " + favoritoConsultaDTO.getGenero() + "\n" +
                "Elenco: " + favoritoConsultaDTO.getElenco() + "\n" +
                "Classificação: " + favoritoConsultaDTO.getClassificacao() + "\n" +
                "Duração: " + favoritoConsultaDTO.getDuracao() + " min\n" +
                "Plataforma: " + favoritoConsultaDTO.getPlataforma() + "\n" +
                "Ano de Lançamento: " + favoritoConsultaDTO.getAnoLancamento() + "\n" +
                "Data de Adição: " + favoritoConsultaDTO.getDataAdicao() + "\n" +
                "ID do Usuário: " + favoritoConsultaDTO.getIdUsuario();
    }

    private String buildQRCodeContent(FavoritoGameDTO favoritoGameDTO) {
        return "ID: " + favoritoGameDTO.getId() + "\n" +
                "Título: " + favoritoGameDTO.getTitulo() + "\n" +
                "Descrição: " + favoritoGameDTO.getDescricao() + "\n" +
                "Gênero: " + favoritoGameDTO.getGenero() + "\n" +
                "Ano de Lançamento: " + favoritoGameDTO.getAnoLancamento() + "\n" +
                "Plataforma: " + favoritoGameDTO.getPlataforma() + "\n" +
                "Console: " + favoritoGameDTO.getConsole() + "\n" +
                "Classificação: " + favoritoGameDTO.getClassificacao() + "\n" +
                "Estúdio de Produção: " + favoritoGameDTO.getEstudioProducao() + "\n" +
                "Nome do Usuário: " + favoritoGameDTO.getNomeUsuario() + "\n" +
                "Data do Favorito: " + favoritoGameDTO.getDataFavorito() + "\n" +
                "ID do Usuário: " + favoritoGameDTO.getIdUsuario() + "\n" +
                "ID do Jogo: " + favoritoGameDTO.getIdJogo();
    }
}
