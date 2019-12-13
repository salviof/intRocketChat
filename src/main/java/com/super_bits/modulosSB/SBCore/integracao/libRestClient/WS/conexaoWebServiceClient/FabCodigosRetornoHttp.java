/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient;

/**
 *
 *
 * Conforme RFC RFC7231 (IETF):
 * http://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
 *
 * @author Sálvio Furbino
 */
public enum FabCodigosRetornoHttp {

    /**
     * iNFORMATIVO
     */
    COD_100XX,
    /**
     * Mudando protocolos
     */
    COD_101,
    /**
     * Processamento (WebDAV) (RFC 2518)
     */
    COD_102,
    /**
     * Pedido-URI muito longo
     */
    COD_122,
    /**
     * SUCESSO!
     */
    COD_2xx,
    /**
     * Criado
     */
    COD_201,
    /**
     * Aceito
     */
    COD_202,
    /**
     * Informação não-autorizada (desde HTTP/1.1)
     */
    COD_203,
    /**
     * Nenhum, conteúdo
     */
    COD_204,
    /**
     * Reset conteúdo (
     */
    COD_205,
    /**
     *
     * Parte de conteúdo
     */
    COD_206,
    /**
     * -Status Multi (WebDAV) (RFC 4918)
     */
    COD_207,
    /**
     * Multiple Choices
     */
    COD_300,
    /**
     * Movido,
     */
    COD_301,
    /**
     * Encontrado,
     */
    COD_302,
    /**
     * Consulte, Outros,
     */
    COD_303,
    /**
     * Não, modificado,
     */
    COD_304,
    /**
     * Use, Proxy(desde, HTTP / 1.1),
     */
    COD_305,
    /**
     * Proxy, Switch,
     */
    COD_306,
    /**
     * Redirecionamento, temporário(desde, HTTP / 1.1),
     */
    COD_307,
    /**
     * Redirecionamento,permanente(RFC
     */
    COD_308,
    /**
     * Erro de cliente
     */
    COD_4xx,
    /**
     * Requisição, inválida,
     */
    COD_400,
    /**
     * Não, autorizado
     */
    COD_401,
    /**
     * Pagamento, necessário
     */
    COD_402,
    /**
     * Proibido
     */
    COD_403,
    /**
     * Não, encontrado,
     */
    COD_404,
    /**
     * Método, não, permitido,
     */
    COD_405,
    /**
     * Não, Aceitável,
     */
    COD_406,
    /**
     * Autenticação, de, proxy, necessária,
     */
    COD_407,
    /**
     * Tempo, de, requisição,esgotou(Timeout)
     */
    COD_408,
    /**
     * Conflito
     */
    COD_409,
    /**
     * Gone,
     */
    COD_410,
    /**
     * comprimento, necessário,
     */
    COD_411,
    /**
     * Précondição falhou
     */
    COD_412,
    /**
     * Entidade, de, solicitação, muito, grande,
     */
    COD_413,
    /**
     * Pedido
     *
     * -URI Too Long
     */
    COD_414,
    /**
     * Tipo de mídia não suportado
     */
    COD_415,
    /**
     * Solicitada, de, Faixa, Não, Satisfatória,
     */
    COD_416,
    /**
     * Falha, na, expectativa,
     */
    COD_417,
    /**
     * 418-420	Não Atribuido
     */
    COD_418,
    /**
     * Entidade, improcessável(WebDAV) (RFC * 4918)
     */
    COD_499,
    /**
     * Indisponível por motivos Legais
     */
    COD_451,
    /**
     * Erro no serviço
     */
    COD_5XX,
    /**
     * Erro, interno do servidor (Internal Server Error)
     */
    COD_500,
    /**
     * Não, implementado(Not, implemented)
     */
    COD_501,
    /**
     * Bad, Gateway
     */
    COD_502,
    /**
     * Serviço, indisponível(Service, Unavailable)
     */
    COD_503,
    /**
     * Gateway Timeout
     */
    COD_504,
    /**
     * HTTP Version not supported
     */
    COD_505,
    /**
     * Variant Also Negotiates	[RFC2295]
     */
    COD_506,
    /**
     * Insufficient Storage	[RFC4918]
     */
    COD_507,
    /**
     * Loop Detected	[RFC5842]
     */
    COD_508,
    /**
     * Unassigned
     */
    COD_509,
    /**
     * Not Extended
     */
    COD_510,
    /**
     * Network Authentication Required	[RFC6585]
     */
    COD_511;

}
