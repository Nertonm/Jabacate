document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('contactForm').addEventListener('submit', function(event) {
    event.preventDefault();
    window.location.href = 'page2.html';
  });

  fetch('https://api.quotable.io/random')
    .then(response => response.json())
    .then(data => {
      document.getElementById('quoteText').textContent = data.content;
      document.getElementById('quoteAuthor').textContent = `- ${data.author}`;
    })
    .catch(error => {
      console.error('Error fetching quote:', error);
      document.getElementById('quoteText').textContent = 'An error occurred while fetching the quote.';
    });
});