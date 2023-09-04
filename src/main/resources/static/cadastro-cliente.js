function addEndereco() {
  let cep = document.getElementById('cep').value;
  let logradouro = document.getElementById('logradouro').value;
  let numero = document.getElementById('numero').value;
  let complemento = document.getElementById('complemento').value;
  let bairro = document.getElementById('bairro').value;
  let cidade = document.getElementById('cidade').value;
  let estado = document.getElementById('estado').value;
  let lista = document.getElementById('lista-enderecos');

  let s = `<li class="list-group-item d-flex justify-content-between align-items-center" style="padding-left: 0px">
                <div>
                  <p class="font-weight-normal">CEP: ` + cep + `</p>
                  <input name="cep" type="hidden" value="` + cep + `">
                  <p class="font-weight-light" name="logradouro">Logradouro: ` + logradouro + `</p>
                  <input name="logradouro" type="hidden" value="` + logradouro + `">
                  <p class="font-weight-light" name="numero">Número: ` + numero + `</p>
                  <input name="numero" type="hidden" value="` + numero + `">
                  <p class="font-weight-light" name="complemento">Complemento: ` + complemento + `</p>
                  <input name="complemento" type="hidden" value="` + complemento + `">
                  <p class="font-weight-light" name="bairro">Bairro: ` + bairro + `</p>
                  <input name="bairro" type="hidden" value="` + bairro + `">
                  <p class="font-weight-light" name="cidade">Cidade: ` + cidade + `</p>
                  <input name="cidade" type="hidden" value="` + cidade + `">
                  <p class="font-weight-light" name="estado">Estado: ` + estado + `</p>
                  <input name="estado" type="hidden" value="` + estado + `">
                </div>
                <button type="button" class="btn btn-danger sm" onclick="$(this).closest('li').remove();">Remover</button>
              </li>`;

  lista.insertAdjacentHTML("beforeend", s);

  document.getElementById('cep').value = '';
  document.getElementById('logradouro').value = '';
  document.getElementById('numero').value = '';
  document.getElementById('complemento').value = '';
  document.getElementById('bairro').value = '';
  document.getElementById('cidade').value = '';
  document.getElementById('estado').value = '';

}

function validar() {
  let e = document.getElementsByClassName('is-invalid');
  if (e.length > 0) {
    alert("Atenção! Corrija os erros do formulário.");
  } else if (document.getElementById('senha').value.length == 0){
    alert("Senha em branco!")
  }else if (document.getElementById('cpf').value.length == 0){
    alert("CPF em branco!")
  }else if (document.getElementById('cep_faturamento').value.length == 0){
    alert("Preencha o endereço de faturamento!")
  }else {
    document.getElementById('form').submit();
  }

}

function validaCPF() {
  let cpf = document.getElementById('cpf').value;
  if (!TestaCPF(cpf)) {
    alert("CPF inválido!");
    document.getElementById('cpf').classList.add("is-invalid");
  } else {
    document.getElementById('cpf').classList.add("is-valid");
    document.getElementById('cpf').classList.remove("is-invalid");
  }
}

function validaSenhas() {
  let senha = document.getElementById('senha').value;
  let repetirSenha = document.getElementById('repetirSenha').value;
  if (senha !== repetirSenha || senha.length == 0) {
    alert("Senhas não conferem!");
    document.getElementById('senha').classList.add("is-invalid");
    document.getElementById('repetirSenha').classList.add("is-invalid");
  } else {
    document.getElementById('senha').classList.add("is-valid");
    document.getElementById('senha').classList.remove("is-invalid");
    document.getElementById('repetirSenha').classList.add("is-valid");
    document.getElementById('repetirSenha').classList.remove("is-invalid");
  }
}

function TestaCPF(strCPF) {
  let Soma;
  let Resto;
  Soma = 0;
  if (strCPF == "00000000000")
    return false;

  for (i = 1; i <= 9; i++)
    Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
  Resto = (Soma * 10) % 11;

  if ((Resto == 10) || (Resto == 11))
    Resto = 0;
  if (Resto != parseInt(strCPF.substring(9, 10)))
    return false;

  Soma = 0;
  for (i = 1; i <= 10; i++)
    Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
  Resto = (Soma * 10) % 11;

  if ((Resto == 10) || (Resto == 11))
    Resto = 0;
  if (Resto != parseInt(strCPF.substring(10, 11)))
    return false;
  return true;
}

function limpa_formulário_cep() {
  //Limpa valores do formulário de cep.
  document.getElementById('logradouro_faturamento').value = ("");
  document.getElementById('bairro_faturamento').value = ("");
  document.getElementById('cidade_faturamento').value = ("");
  document.getElementById('estado_faturamento').value = ("");
}

function limpa_formulário_cep1() {
  //Limpa valores do formulário de cep.
  document.getElementById('logradouro_entrega').value = ("");
  document.getElementById('bairro_entrega').value = ("");
  document.getElementById('cidade_entrega').value = ("");
  document.getElementById('estado_entrega').value = ("");
}

function limpa_formulário_cep2() {
  //Limpa valores do formulário de cep.
  document.getElementById('logradouro').value = ("");
  document.getElementById('bairro').value = ("");
  document.getElementById('cidade').value = ("");
  document.getElementById('estado').value = ("");
}

function meu_callback(conteudo) {
  if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('logradouro_faturamento').value = (conteudo.logradouro);
    document.getElementById('bairro_faturamento').value = (conteudo.bairro);
    document.getElementById('cidade_faturamento').value = (conteudo.localidade);
    document.getElementById('estado_faturamento').value = (conteudo.uf);
  } //end if.
  else {
    //CEP não Encontrado.
    limpa_formulário_cep();
    alert("CEP não encontrado.");
  }
}

function meu_callback1(conteudo) {
  if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('logradouro_entrega').value = (conteudo.logradouro);
    document.getElementById('bairro_entrega').value = (conteudo.bairro);
    document.getElementById('cidade_entrega').value = (conteudo.localidade);
    document.getElementById('estado_entrega').value = (conteudo.uf);
  } //end if.
  else {
    //CEP não Encontrado.
    limpa_formulário_cep();
    alert("CEP não encontrado.");
  }
}

function meu_callback2(conteudo) {
  if (!("erro" in conteudo)) {
    //Atualiza os campos com os valores.
    document.getElementById('logradouro').value = (conteudo.logradouro);
    document.getElementById('bairro').value = (conteudo.bairro);
    document.getElementById('cidade').value = (conteudo.localidade);
    document.getElementById('estado').value = (conteudo.uf);
  } //end if.
  else {
    //CEP não Encontrado.
    limpa_formulário_cep2();
    alert("CEP não encontrado.");
  }
}

function pesquisacep(valor) {

  //Nova variável "cep" somente com dígitos.
  var cep = valor.replace(/\D/g, '');

  //Verifica se campo cep possui valor informado.
  if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if (validacep.test(cep)) {

      //Preenche os campos com "..." enquanto consulta webservice.
      document.getElementById('logradouro_faturamento').value = "...";
      document.getElementById('bairro_faturamento').value = "...";
      document.getElementById('cidade_faturamento').value = "...";
      document.getElementById('estado_faturamento').value = "...";

      //Cria um elemento javascript.
      var script = document.createElement('script');

      //Sincroniza com o callback.
      script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback';


      //Insere script no documento e carrega o conteúdo.
      document.body.appendChild(script);

      console.log(script)

    } //end if.
    else {
      //cep é inválido.
      limpa_formulário_cep();
      alert("Formato de CEP inválido.");
    }
  } //end if.
  else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep();
  }
}

function espelharEndereco(check) {
  if (check) {
    document.getElementById('cep_entrega').value = document.getElementById('cep_faturamento').value;
    document.getElementById('logradouro_entrega').value = document.getElementById('logradouro_faturamento').value;
    document.getElementById('numero_entrega').value = document.getElementById('numero_faturamento').value;
    document.getElementById('complemento_entrega').value = document.getElementById('complemento_faturamento').value;
    document.getElementById('bairro_entrega').value = document.getElementById('bairro_faturamento').value;
    document.getElementById('cidade_entrega').value = document.getElementById('cidade_faturamento').value;
    document.getElementById('estado_entrega').value = document.getElementById('estado_faturamento').value;
  } else {
    document.getElementById('cep_entrega').value = "";
    document.getElementById('logradouro_entrega').value = "";
    document.getElementById('numero_entrega').value = "";
    document.getElementById('complemento_entrega').value = "";
    document.getElementById('bairro_entrega').value = "";
    document.getElementById('cidade_entrega').value = "";
    document.getElementById('estado_entrega').value = "";
  }
}


function pesquisacep1(valor) {

  //Nova variável "cep" somente com dígitos.
  var cep = valor.replace(/\D/g, '');

  //Verifica se campo cep possui valor informado.
  if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if (validacep.test(cep)) {

      //Preenche os campos com "..." enquanto consulta webservice.
      document.getElementById('logradouro_entrega').value = "...";
      document.getElementById('bairro_entrega').value = "...";
      document.getElementById('cidade_entrega').value = "...";
      document.getElementById('estado_entrega').value = "...";

      //Cria um elemento javascript.
      var script = document.createElement('script');

      //Sincroniza com o callback.
      script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback1';


      //Insere script no documento e carrega o conteúdo.
      document.body.appendChild(script);

      console.log(script)

    } //end if.
    else {
      //cep é inválido.
      limpa_formulário_cep1();
      alert("Formato de CEP inválido.");
    }
  } //end if.
  else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep1();
  }
}

function pesquisacep2(valor) {

  //Nova variável "cep" somente com dígitos.
  var cep = valor.replace(/\D/g, '');

  //Verifica se campo cep possui valor informado.
  if (cep != "") {

    //Expressão regular para validar o CEP.
    var validacep = /^[0-9]{8}$/;

    //Valida o formato do CEP.
    if (validacep.test(cep)) {

      //Preenche os campos com "..." enquanto consulta webservice.
      document.getElementById('logradouro').value = "...";
      document.getElementById('bairro').value = "...";
      document.getElementById('cidade').value = "...";
      document.getElementById('estado').value = "...";

      //Cria um elemento javascript.
      var script = document.createElement('script');

      //Sincroniza com o callback.
      script.src = 'https://viacep.com.br/ws/' + cep + '/json/?callback=meu_callback2';


      //Insere script no documento e carrega o conteúdo.
      document.body.appendChild(script);

      console.log(script)

    } //end if.
    else {
      //cep é inválido.
      limpa_formulário_cep2();
      alert("Formato de CEP inválido.");
    }
  } //end if.
  else {
    //cep sem valor, limpa formulário.
    limpa_formulário_cep2();
  }
}