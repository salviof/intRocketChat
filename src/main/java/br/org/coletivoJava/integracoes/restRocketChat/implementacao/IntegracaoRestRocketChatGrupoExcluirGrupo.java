package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfUsuario;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_EXCLUIR_GRUPO)
public class IntegracaoRestRocketChatGrupoExcluirGrupo
        extends
        AcaoApiIntegracaoAbstrato {

    public IntegracaoRestRocketChatGrupoExcluirGrupo(
            final FabTipoAgenteClienteApi pTipoAgente,
            final ItfUsuario pUsuario, final java.lang.Object... pParametro) {
        super(FabApiRestRocketChatV1Channel.GRUPO_EXCLUIR_GRUPO, pTipoAgente,
                pUsuario, pParametro);
    }

    @Override
    public String gerarCorpoRequisicao() {
        if (getQuantidadeParametrosEnviados() != 1) {
            throw new UnsupportedOperationException("Esperado um parametro para criação do grupo (Parametro nome)");
        }
        return "{ \"roomId\": \"" + parametros.get(0) + "\" }";
    }

}
