/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.org.coletivoJava.fw.erp.implementacao.chatService;

import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ComoUsuario;
import java.util.List;

/**
 *
 * @author salvio
 */
public interface ItfErpChatService {

    public ComoChatSalaBeanRC getChat(String nomeSala);

    public ComoChatSalaBeanRC getChatCriandoSeNaoExistir(String nomeSala) throws ErroConexaoServicoChat;

    public boolean excluirSala(ComoChatSalaBeanRC nomeSala) throws ErroConexaoServicoChat;

    public boolean autenticarSessao(String pEmail, String pSenha) throws ErroConexaoServicoChat;

    public List<ComoUsuarioChatRC> atualizarListaDeUsuarios() throws ErroConexaoServicoChat;

    public ComoUsuarioChatRC getUsuario(String pEmail) throws ErroConexaoServicoChat;

    public ComoUsuarioChatRC getUsuarioByCodigo(String pCodigo) throws ErroConexaoServicoChat;

    public List<ComoUsuarioChatRC> getUsuarios();

    public ComoUsuarioChatRC criarUsuario(ComoUsuario pUsuario) throws ErroConexaoServicoChat;

    public ComoUsuarioChatRC criarUsuario(ComoUsuario pUsuario, String pSenha) throws ErroConexaoServicoChat;

    public boolean adicionarUsuario(ComoChatSalaBeanRC pSala, String pEmailSenha) throws ErroConexaoServicoChat;

    public ComoUsuarioChatRC getUsuarioLogado();

    public ComoUsuarioChatRC efetuarLogin(ComoUsuario pUsuario);

    public ComoUsuarioChatRC efetuarLogin(ComoUsuario pUsuario, String pSenha);

    public ComoChatSalaBeanRC getSalaAtualizada(ComoChatSalaBeanRC pSala);

}
