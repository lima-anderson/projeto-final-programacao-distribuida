package com.lima.msagenda.feingClients;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lima.msagenda.models.EmailModel;
import com.lima.msagenda.models.dtos.EmailDto;

@Component
@FeignClient(name = "emailService", url = "localhost:8083", path = "/enviar-email")
public interface ExpedidorEmailFeingCLeint {

	@PostMapping
	ResponseEntity<EmailModel> enviarEmail(@RequestBody @Valid EmailDto emailDto);

}
