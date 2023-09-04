

function validar() {
  let username = document.getElementById('username');
  let email = document.getElementById('email');
  let expiry_month = document.getElementById('expiry_month');
  let expiry_year = document.getElementById('expiry_year');
  let password_confirm = document.getElementById('password_confirm');
  
  
  let e = document.getElementsByClassName('is-invalid');
  if (e.length > 0) {
    alert("Atenção! Dados inválidos do cartão.");
  }else {
    document.getElementById('form').submit();
  }

}

function validaUsername() {
  let username = document.getElementById('username').value;
  if (username.length < 10) {
    alert("Nome impresso inválido!");
    document.getElementById('username').classList.add("is-invalid");
  } else {
    document.getElementById('username').classList.add("is-valid");
    document.getElementById('username').classList.remove("is-invalid");
  }
}

function validaNumeroCartao() {
  let email = document.getElementById('email').value;
  if (email.length != 16) {
    alert("Número inválido!");
    document.getElementById('email').classList.add("is-invalid");
  } else {
    document.getElementById('email').classList.add("is-valid");
    document.getElementById('email').classList.remove("is-invalid");
  }
}

function validaExpiry_year() {
  let expiry_year = document.getElementById('expiry_year').value;
  if (expiry_year.length == '') {
    document.getElementById('expiry_year').classList.add("is-invalid");
  } else {
    document.getElementById('expiry_year').classList.add("is-valid");
    document.getElementById('expiry_year').classList.remove("is-invalid");
  }
}

function validaSenha() {
  let password_confirm = document.getElementById('password_confirm').value;
  if (password_confirm.length != 3) {
    document.getElementById('password_confirm').classList.add("is-invalid");
  } else {
    document.getElementById('password_confirm').classList.add("is-valid");
    document.getElementById('password_confirm').classList.remove("is-invalid");
  }
}


function validaExpiry_month() {
  let expiry_month = document.getElementById('expiry_month').value;
  if (expiry_month.length == '') {
    document.getElementById('expiry_month').classList.add("is-invalid");
  } else {
    document.getElementById('expiry_month').classList.add("is-valid");
    document.getElementById('expiry_month').classList.remove("is-invalid");
  }
}