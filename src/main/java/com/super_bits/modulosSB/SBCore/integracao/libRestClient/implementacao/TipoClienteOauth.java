/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.implementacao;

import com.super_bits.modulosSB.SBCore.integracao.libRestClient.api.FabTipoAgenteClienteRest;

import com.super_bits.modulosSB.SBCore.modulos.fabrica.ItfFabrica;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.Interfaces.basico.ItfBeanVinculadoAEnum;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.ItemSimples;

/**
 *
 * @author SalvioF
 */
@InfoObjetoSB(plural = "tipo Clientes", tags = {"Tipo Cliente"}, fabricaVinculada = FabTipoAgenteClienteRest.class)
public class TipoClienteOauth extends ItemSimples implements ItfBeanVinculadoAEnum {

    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private int id;
    @InfoCampo(tipo = FabTipoAtributoObjeto.AAA_NOME)
    private String nome;
    private FabTipoAgenteClienteRest enumVinculado;

    @Override
    public void setEnumVinculado(ItfFabrica pFabrica) {
        enumVinculado = (FabTipoAgenteClienteRest) pFabrica;
    }

    @Override
    public FabTipoAgenteClienteRest getEnumVinculado() {
        return enumVinculado;
    }

}
