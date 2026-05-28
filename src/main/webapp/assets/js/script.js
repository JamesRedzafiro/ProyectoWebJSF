
// Expandir/Contraer FAQ
function toggleFAQ(element) {
    const faqItem = element.parentElement;
    const faqAnswer = faqItem.querySelector('.faq-answer');
    const faqToggle = element.querySelector('.faq-toggle');
    
    // Toggle visibilidad
    if (faqAnswer.style.display === 'none' || faqAnswer.style.display === '') {
        faqAnswer.style.display = 'block';
        faqToggle.textContent = '−'; // Cambiar a menos
        faqAnswer.style.maxHeight = faqAnswer.scrollHeight + 'px';
        faqAnswer.style.transition = 'max-height 0.3s ease-out';
    } else {
        faqAnswer.style.maxHeight = '0px';
        faqAnswer.style.overflow = 'hidden';
        faqToggle.textContent = '+'; // Cambiar a más
        setTimeout(function() {
            faqAnswer.style.display = 'none';
        }, 300);
    }
}

// Mostrar/ocultar contraseña
document.addEventListener('DOMContentLoaded', function() {
    // Crear botón de mostrar/ocultar contraseña si existe campo password
    const passwordInputs = document.querySelectorAll('input[type="password"]');
    
    passwordInputs.forEach(passwordInput => {
        const wrapper = passwordInput.parentElement;
        const toggleBtn = document.createElement('button');
        toggleBtn.type = 'button';
        toggleBtn.textContent = ' Mostrar';
        toggleBtn.style.position = 'absolute';
        toggleBtn.style.right = '10px';
        toggleBtn.style.top = '50%';
        toggleBtn.style.transform = 'translateY(-50%)';
        toggleBtn.style.background = 'none';
        toggleBtn.style.border = 'none';
        toggleBtn.style.cursor = 'pointer';
        toggleBtn.style.fontSize = '12px';
        
        toggleBtn.addEventListener('click', function(e) {
            e.preventDefault();
            if (passwordInput.type === 'password') {
                passwordInput.type = 'text';
                toggleBtn.textContent = ' Ocultar';
            } else {
                passwordInput.type = 'password';
                toggleBtn.textContent = ' Mostrar';
            }
        });
        
        wrapper.style.position = 'relative';
        wrapper.appendChild(toggleBtn);
    });
});

// Scroll suave para anclas
document.querySelectorAll('a[href^="#"]').forEach(anchor => {
    anchor.addEventListener('click', function(e) {
        const href = this.getAttribute('href');
        if (href !== '#') {
            e.preventDefault();
            const target = document.querySelector(href);
            if (target) {
                target.scrollIntoView({ behavior: 'smooth' });
            }
        }
    });
});
