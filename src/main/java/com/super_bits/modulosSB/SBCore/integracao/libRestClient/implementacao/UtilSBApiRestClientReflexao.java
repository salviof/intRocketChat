/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.ConfigGeral.arquivosConfiguracao.ConfigModulo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringSlugs;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilSBCoreStringsCammelCase;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.servicoRegistrado.InfoConfigRestClientIntegracao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfValidacao;
import org.reflections.ReflectionUtils;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.ItfFabricaIntegracaoRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.token.ItfTokenGestao;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfAcaoApiRest;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.transmissao_recepcao_rest_client.ItfApiRestHeaderPadrao;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcesso;
import com.super_bits.modulosSB.SBCore.modulos.Mensagens.FabTipoAgenteDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 * @since 11/12/2019
 * @version 1.0
 */
public class UtilSBApiRestClientReflexao {

    public static InfoConfigRestClientIntegracao getInfoConfigWebService(ItfFabricaIntegracaoRest p) {
        return p.getClass().getAnnotation(InfoConfigRestClientIntegracao.class);
    }

    public static String getNomeClasseAnotacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        return "InfoIntegracaoRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiusculaSemCaracterEspecial(info.nomeIntegracao()).replace("-", " ");
    }

    public static String getNomeClasseImplementacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();

        return "IntegracaoRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(p.toString());
    }

    public static String getNomeClasseImplementacaoGestaoToken(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();

        return "GestaoTokenRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome);
    }

    public static String getNomeClasseImplementacaoGestaoHeaderPadrao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();

        return "IntegracaoRest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + "_HeaderPadrao";
    }

    public static ItfApiRestHeaderPadrao getHeaderPadrao(ItfFabricaIntegracaoRest fabrica, ItfAcaoApiRest p) {
        String caminhoCompleto = getPacoteImplementacao(fabrica)
                + "." + getNomeClasseImplementacaoGestaoHeaderPadrao(fabrica);
        Class classeHeaderPadrao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        try {
            return (ItfApiRestHeaderPadrao) classeHeaderPadrao.getConstructor(ItfAcaoApiRest.class).newInstance(p);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(UtilSBApiRestClientReflexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String getPacoteApi(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName()
                    + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();
        return "br.org.coletivoJava.integracoes.rest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + ".api";
    }

    public static String getPacoteImplementacao(ItfFabricaIntegracaoRest p) {
        InfoConfigRestClientIntegracao info = getInfoConfigWebService(p);
        if (info == null) {
            throw new UnsupportedOperationException(InfoConfigRestClientIntegracao.class.getSimpleName() + "Não foi definido para" + p.getClass().getSimpleName());
        }
        String nome = info.nomeIntegracao();
        return "br.org.coletivoJava.integracoes.rest" + UtilSBCoreStringsCammelCase.getCamelByTextoPrimeiraLetraMaiuscula(nome) + ".implementacao";
    }

    public static Class getClasseAnotacao(ItfFabricaIntegracaoRest pApi) {
        String caminhoCompleto = getPacoteApi(pApi)
                + "." + getNomeClasseAnotacao(pApi);
        Class classeAnotacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        return classeAnotacao;
    }

    public static Class getClasseImplementacao(ItfFabricaIntegracaoRest pApi) {
        String caminhoCompleto = getPacoteImplementacao(pApi)
                + "." + getNomeClasseImplementacao(pApi);
        Class classeValidacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        return classeValidacao;
    }

    public static Class getClasseToken(ItfFabricaIntegracaoRest pApi) {
        String caminhoCompleto = getPacoteImplementacao(pApi)
                + "." + getNomeClasseImplementacaoGestaoToken(pApi);
        Class classeAnotacao = (Class<? extends ItfValidacao>) ReflectionUtils.forName(caminhoCompleto);
        return classeAnotacao;
    }

    public static ItfTokenGestao getNovaInstanciaGestaoAutenticador(ItfFabricaIntegracaoRest pApi, FabTipoAgenteClienteRest pTipoAgente, ItfUsuario pUsuario) {
        Class classe = getClasseToken(pApi);
        try {
            ItfTokenGestao pNovaGestaoToken = (ItfTokenGestao) classe.getConstructor(FabTipoAgenteClienteRest.class, ItfUsuario.class).newInstance(pTipoAgente, pUsuario);
            return pNovaGestaoToken;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro obtendo gestor de tolen do cliente rest para:" + pApi, t);
        }
        return null;

    }

    public static ConfigModulo getConfigmodulo(ItfFabricaIntegracaoRest pApi) {
        return getConfigmodulo(pApi.getClass());
    }

    public static ConfigModulo getConfigmodulo(Class<? extends ItfFabricaIntegracaoRest> pApi) {
        InfoConfigRestClientIntegracao informacoes = pApi.getAnnotation(InfoConfigRestClientIntegracao.class);
        return SBCore.getConfigModulo(informacoes.configuracao());
    }
}
