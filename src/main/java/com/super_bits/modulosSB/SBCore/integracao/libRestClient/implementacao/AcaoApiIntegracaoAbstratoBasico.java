/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.gestaoToken.MapaTokensGerenciados;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.RespostaWebServiceRestIntegracao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ConsumoWSExecucao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.FabTipoConexaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.InfoConsumoRestService;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.RespostaWebServiceSimples;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import java.util.Map;
import org.coletivojava.fw.api.tratamentoErros.FabErro;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

/**
 *
 * @author desenvolvedor
 */
public abstract class AcaoApiIntegracaoAbstratoBasico implements ItfAcaoApiRest {

    private ConsumoWSExecucao consumoWS;
    protected ItfFabricaIntegracaoRest fabricaIntegracao;
    protected InfoConsumoRestService infoRest;
    private String corpoRequisicaoGerado;
    private String urlRequisicaoGerada;
    private ItfTokenGestao tokenGestao;
    private FabTipoConexaoRest tipoRequisicao;
    private Map<String, String> cabecalhoGerado;
    private boolean postarInformacoes;
    private RespostaWebServiceRestIntegracao resposta;
    private ItfUsuario usuario;
    private String token;
    protected Object[] parametros;
    private FabTipoAgenteClienteRest tipoAgente;

    public AcaoApiIntegracaoAbstratoBasico(ItfFabricaIntegracaoRest pIntegracaoEndpoint, FabTipoAgenteClienteRest pTipoAgente, ItfUsuario pUsuario, Object... pParametros) {
        this.fabricaIntegracao = pIntegracaoEndpoint;
        infoRest = UtilSBApiRestClient.getInformacoesConsumoRest(pIntegracaoEndpoint);
        parametros = pParametros;
        usuario = pUsuario;
        if (usuario == null) {
            tipoAgente = FabTipoAgenteClienteRest.SISTEMA;
        } else {
            tipoAgente = pTipoAgente;
        }
        try {
            executarAcao();
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro criando ação de integração Rest em:" + pIntegracaoEndpoint, t);
        }

    }

    public ConfigModulo getConfiguracao() {
        return fabricaIntegracao.getConfiguracao();
    }

    @Override
    public String gerarUrlRequisicao() {
        if (infoRest == null) {
            return getUrlServidor();
        } else {
            return (getUrlServidor() + infoRest.getPachServico());
        }
    }

    @Override
    public String gerarCorpoRequisicao() {
        return "";
    }

    private boolean defineRequisicaoPostaInformacoes() {
        switch (tipoRequisicao) {
            case POST:
            case PUT:
            case DELET:
                return true;

            case GET:
                break;

            case PATCH:
                break;
            case INDETERMINADO:

                break;

            default:
                throw new AssertionError(infoRest.tipoConexao().name());

        }
        return false;

    }

    @Override
    public Map<String, String> gerarCabecalho() {

        return UtilSBApiRestClientReflexao.getHeaderPadrao(fabricaIntegracao, this).getHeaderPadrao();
    }

    @Override
    public FabTipoConexaoRest gerarTipoRequisicao() {

        if (fabricaIntegracao.getInformacoesConsumo().tipoConexao().equals(FabTipoConexaoRest.INDETERMINADO)) {
            if (this.toString().contains("CTR")) {
                return FabTipoConexaoRest.PUT;
            }
            if (this.toString().contains("SELECAO")) {
                return FabTipoConexaoRest.PATCH;
            }

            return FabTipoConexaoRest.GET;

        } else {
            return fabricaIntegracao.getInformacoesConsumo().tipoConexao();
        }
    }

    private void executarAcao() {

        urlRequisicaoGerada = gerarUrlRequisicao();

        tipoRequisicao = gerarTipoRequisicao();
        postarInformacoes = defineRequisicaoPostaInformacoes();
        corpoRequisicaoGerado = gerarCorpoRequisicao();
        cabecalhoGerado = gerarCabecalho();

        consumoWS = new ConsumoWSExecucao() {

            @Override
            public RespostaWebServiceSimples efetuarConexao() {
                return UtilSBApiRestClient.getRespostaRest(
                        urlRequisicaoGerada, tipoRequisicao, postarInformacoes, cabecalhoGerado, corpoRequisicaoGerado);

            }

        };

        gerarResposta(consumoWS);
    }

    @Override
    public boolean isMetodoEnviaDados() {
        return ((tipoRequisicao.equals(FabTipoConexaoRest.PUT)) || (tipoRequisicao.equals(FabTipoConexaoRest.POST)));
    }

    @Override
    public void gerarResposta(ConsumoWSExecucao pConsumoRest) {
        consumoWS.start();
        resposta = pConsumoRest.getResposta();
    }

    @Override
    public String getUrlServidor() {
        return urlRequisicaoGerada;
    }

    @Override
    public RespostaWebServiceSimples getResposta() {
        return resposta;
    }

    @Override
    public ItfTokenGestao getTokenGestao() {
        if (tokenGestao == null) {
            switch (tipoAgente) {
                case USUARIO:
                    tokenGestao = MapaTokensGerenciados.getAutenticadorUsuarioLogado(fabricaIntegracao, usuario);
                    if (tokenGestao == null) {
                        tokenGestao = UtilSBApiRestClientReflexao.getNovaInstanciaGestaoAutenticador(fabricaIntegracao, tipoAgente, usuario);
                        MapaTokensGerenciados.registrarAutenticador(tokenGestao, fabricaIntegracao);
                    }
                    break;
                case SISTEMA:
                    tokenGestao = MapaTokensGerenciados.getAutenticadorSistemaAtual(fabricaIntegracao);
                    if (tokenGestao == null) {
                        tokenGestao = UtilSBApiRestClientReflexao.getNovaInstanciaGestaoAutenticador(fabricaIntegracao, tipoAgente, usuario);
                        MapaTokensGerenciados.registrarAutenticador(tokenGestao, fabricaIntegracao);
                    }
                    break;
                default:
                    throw new AssertionError(tipoAgente.name());

            }
        }

        return tokenGestao;
    }

}
