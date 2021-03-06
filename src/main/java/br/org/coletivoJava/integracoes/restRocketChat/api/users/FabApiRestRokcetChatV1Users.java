/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.coletivoJava.integracoes.restRocketChat.api.users;

import br.org.coletivoJava.integracoes.restRocketChat.api.FabConfigRocketChat;
import br.org.coletivoJava.integracoes.restRocketChat.implementacao.GestaoTokenRestRocketChat;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.FabTipoAutenticacaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author sfurbino
 */
@InfoConfigRestClientIntegracao(enderecosDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/",
        tipoAutenticacao = FabTipoAutenticacaoRest.CHAVE_ACESSO_METODOLOGIA_PROPRIA,
        nomeIntegracao = FabConfigRocketChat.NOME_APLICACAO,
        configuracao = FabConfigRocketChat.class
)
public enum FabApiRestRokcetChatV1Users implements ItfFabricaIntegracaoRest {

    @InfoConsumoRestService(getPachServico = "/api/v1/users.list",
            tipoConexao = FabTipoConexaoRest.GET,
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/users/list/")
    USUARIOS_LISTAR,
    /**
     * Aquardando este
     * requisito:https://github.com/RocketChat/Rocket.Chat/pull/13634
     */
    @InfoConsumoRestService(getPachServico = "/api/v1/im.counters",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"username"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    DIRECT_MENSAGENS_CONTADORES,
    @InfoConsumoRestService(getPachServico = "/api/v1/subscriptions.get",
            tipoConexao = FabTipoConexaoRest.GET,
            parametrosGet = {"username"},
            urlDocumentacao = "https://rocket.chat/docs/developer-guides/rest-api/authentication/me/")
    ASSINATURAS_LISTAR,;

    @Override
    public GestaoTokenRestRocketChat getGestaoToken() {
        return (GestaoTokenRestRocketChat) ItfFabricaIntegracaoRest.super.getGestaoToken(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GestaoTokenRestRocketChat getGestaoToken(ItfUsuario pUsuario) {
        return (GestaoTokenRestRocketChat) ItfFabricaIntegracaoRest.super.getGestaoToken(pUsuario); //To change body of generated methods, choose Tools | Templates.

    }

}
