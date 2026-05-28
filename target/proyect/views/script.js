// Validación de login de usuario
function validateLogin(event) {
  event.preventDefault();
  const email = document.getElementById('email').value;
  const password = document.getElementById('password').value;
  const errorMessage = document.getElementById('loginError');

  if (email === 'usuario@email.com' && password === 'usuario') {
    window.location.href = 'citas.html';
  } else {
    errorMessage.style.display = 'block';
    errorMessage.textContent = 'Correo electrónico o contraseña incorrectos';
  }
  return false;
}

// Validación de login de administrador
function validateAdminLogin(event) {
  event.preventDefault();
  const email = document.getElementById('admin-email').value;
  const password = document.getElementById('admin-password').value;
  const errorMessage = document.getElementById('adminLoginError');

  if (email === 'admin@email.com' && password === 'admin') {
    window.location.href = 'admin.html';
  } else {
    errorMessage.style.display = 'block';
    errorMessage.textContent = 'Usuario o contraseña incorrectos';
  }
  return false;
}

// Validación de registro de usuario
function validateRegister(event) {
  const dni = document.getElementById('dni').value;
  const telefono = document.getElementById('telefono').value;
  const password = document.getElementById('password').value;
  const confirmPassword = document.getElementById('confirm-password').value;
  let valid = true;
  let errorMsg = '';

  if (!/^\d{8}$/.test(dni)) {
    errorMsg += 'El DNI debe tener 8 dígitos.\n';
    valid = false;
  }
  if (!/^\d{9}$/.test(telefono)) {
    errorMsg += 'El teléfono debe tener 9 dígitos.\n';
    valid = false;
  }
  if (password !== confirmPassword) {
    errorMsg += 'Las contraseñas no coinciden.';
    valid = false;
  }

  if (!valid) {
    alert(errorMsg);
    event.preventDefault();
  } else {
    alert('Cuenta creada exitosamente. Serás redirigido al login.');
    setTimeout(() => {
      window.location.href = 'login.html';
    }, 2000);
  }
  return valid;
}

// Actualizar perfil de usuario
function updateProfile(event) {
  event.preventDefault();
  
  const nombre = document.getElementById('nombre').value;
  const dni = document.getElementById('dni').value;
  const email = document.getElementById('email').value;
  const telefono = document.getElementById('telefono').value;
  const nuevaPassword = document.getElementById('nueva-password').value;
  const confirmarPassword = document.getElementById('confirmar-password').value;
  
  // Validaciones básicas
  if (!/^\d{8}$/.test(dni)) {
    alert('El DNI debe tener 8 dígitos.');
    return false;
  }
  
  if (!/^\d{9}$/.test(telefono)) {
    alert('El teléfono debe tener 9 dígitos.');
    return false;
  }
  
  if (nuevaPassword && nuevaPassword !== confirmarPassword) {
    alert('Las nuevas contraseñas no coinciden.');
    return false;
  }
  
  alert('Perfil actualizado exitosamente.');
  return false;
}

// Enviar mensaje de contacto
function sendMessage(event) {
  event.preventDefault();
  
  const nombre = document.getElementById('contact-name').value;
  const email = document.getElementById('contact-email').value;
  const asunto = document.getElementById('contact-subject').value;
  const mensaje = document.getElementById('contact-message').value;
  
  if (!nombre || !email || !asunto || !mensaje) {
    alert('Por favor completa todos los campos obligatorios.');
    return false;
  }
  
  alert('Mensaje enviado exitosamente. Te contactaremos pronto.');
  document.querySelector('.contact-form').reset();
  return false;
}

// Funciones para gestión de citas (historial)
function editAppointment(id) {
  alert(`Redirigiendo a editar cita ID: ${id}`);
  // Aquí se implementaría la redirección a página de edición
}

function cancelAppointment(id) {
  if (confirm('¿Estás seguro de que quieres cancelar esta cita?')) {
    alert(`Cita ID: ${id} cancelada exitosamente.`);
    // Aquí se implementaría la lógica de cancelación
  }
}

function addReview(id) {
  const rating = prompt('Califica tu experiencia del 1 al 5:');
  if (rating && rating >= 1 && rating <= 5) {
    alert(`Gracias por tu calificación de ${rating} estrellas.`);
    // Aquí se implementaría la lógica de calificación
  }
}

// Configurar fecha mínima en formularios
document.addEventListener('DOMContentLoaded', function() {
  const dateInputs = document.querySelectorAll('input[type="date"]');
  const today = new Date().toISOString().split('T')[0];
  
  dateInputs.forEach(input => {
    if (input.id === 'fecha') {
      input.min = today;
    }
  });
});

// Función para redirigir después de reservar cita
function confirmReservation() {
  window.location.href = 'confirmacion.html';
}

// Función para toggle de FAQ
function toggleFAQ(element) {
  const answer = element.nextElementSibling;
  const toggle = element.querySelector('.faq-toggle');
  
  if (answer.classList.contains('active')) {
    answer.classList.remove('active');
    toggle.textContent = '+';
  } else {
    // Cerrar todas las otras respuestas
    document.querySelectorAll('.faq-answer.active').forEach(item => {
      item.classList.remove('active');
      item.previousElementSibling.querySelector('.faq-toggle').textContent = '+';
    });
    
    answer.classList.add('active');
    toggle.textContent = '-';
  }
}

// Smooth scroll para navegación interna
document.addEventListener('DOMContentLoaded', function() {
  const dateInputs = document.querySelectorAll('input[type="date"]');
  const today = new Date().toISOString().split('T')[0];
  
  dateInputs.forEach(input => {
    if (input.id === 'fecha') {
      input.min = today;
    }
  });

  // Smooth scroll para enlaces internos
  document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function (e) {
      e.preventDefault();
      const target = document.querySelector(this.getAttribute('href'));
      if (target) {
        target.scrollIntoView({
          behavior: 'smooth',
          block: 'start'
        });
      }
    });
  });
});
