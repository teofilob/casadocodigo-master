package br.com.casadocodigo.loja.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.controllers.dto.PedidoDto;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private RestTemplate  restTemplate; 
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView obterPedodos() throws URISyntaxException{
		
		
		
		ModelAndView modelAndView = new ModelAndView("pedidos/pedido");
		
		
		
		
		List<PedidoDto> pedidos = new ArrayList<>();
		
		URI uri_service = new URI("https://book-payment.herokuapp.com/orders");
		PedidoDto[] retorno =	restTemplate.getForObject(uri_service, PedidoDto[].class);
		
		modelAndView.addObject("pedidos", retorno);
		return modelAndView;
	}
	
}
