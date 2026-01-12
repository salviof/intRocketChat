package br.org.coletivoJava.integracoes.restRocketChat.implementacao;

import br.org.coletivoJava.integracoes.restRocketChat.api.InfoIntegracaoRestRocketChatChannel;
import br.org.coletivoJava.integracoes.restRocketChat.api.channel.FabApiRestRocketChatV1Channel;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao.AcaoApiIntegracaoAbstrato;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteApi;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;

@InfoIntegracaoRestRocketChatChannel(tipo = FabApiRestRocketChatV1Channel.GRUPO_LISTAR_USUARIOS)
public class IntegracaoRestRocketChatGrupoListarUsuarios
		extends
			AcaoApiIntegracaoAbstrato {

	public IntegracaoRestRocketChatGrupoListarUsuarios(
			final FabTipoAgenteClienteApi pTipoAgente,
			final ComoUsuario pUsuario, final java.lang.Object... pParametro) {
		super(FabApiRestRocketChatV1Channel.GRUPO_LISTAR_USUARIOS, pTipoAgente,
				pUsuario, pParametro);
	}
}