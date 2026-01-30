document.getElementById('welcomeTime').textContent = getCurrentTime();

function getCurrentTime() {
    const now = new Date();
    return now.toLocaleTimeString('en-US', { 
        hour: '2-digit', 
        minute: '2-digit' 
    });
}

function handleKeyPress(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
}

function sendMessage() {
    const input = document.getElementById('userInput');
    const message = input.value.trim();
    
    if (message === '') return;
    
    displayMessage(message, 'user');
    input.value = '';
    showTypingIndicator();
    
    fetch('/api/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            message: message,
            timestamp: new Date().toISOString(),
            sender: 'user'
        })
    })
    .then(response => response.json())
    .then(data => {
        removeTypingIndicator();
        displayMessage(data.response, 'bot');
    })
    .catch(error => {
        console.error('Error:', error);
        removeTypingIndicator();
        displayMessage('Sorry, I encountered an error. Please try again! 😔', 'bot');
    });
}

function displayMessage(text, sender) {
    const chatContainer = document.getElementById('chatContainer');
    const messageDiv = document.createElement('div');
    messageDiv.className = `message ${sender}-message`;
    
    const timestamp = getCurrentTime();
    
    if (sender === 'user') {
        messageDiv.innerHTML = `
            <div class="message-content">
                <strong>You:</strong> ${escapeHtml(text)}
            </div>
            <span class="timestamp">${timestamp}</span>
        `;
    } else {
        messageDiv.innerHTML = `
            <div class="message-content">
                <strong>Bot:</strong>
                <p>${formatBotMessage(text)}</p>
            </div>
            <span class="timestamp">${timestamp}</span>
        `;
    }
    
    chatContainer.appendChild(messageDiv);
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

function formatBotMessage(text) {
    text = text.replace(/\n/g, '<br>');
    text = text.replace(/•/g, '<br>•');
    return text;
}

function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

function showTypingIndicator() {
    const chatContainer = document.getElementById('chatContainer');
    const typingDiv = document.createElement('div');
    typingDiv.className = 'message bot-message';
    typingDiv.id = 'typingIndicator';
    typingDiv.innerHTML = `
        <div class="typing-indicator">
            <span></span>
            <span></span>
            <span></span>
        </div>
    `;
    chatContainer.appendChild(typingDiv);
    chatContainer.scrollTop = chatContainer.scrollHeight;
}

function removeTypingIndicator() {
    const indicator = document.getElementById('typingIndicator');
    if (indicator) {
        indicator.remove();
    }
}

function quickMessage(message) {
    document.getElementById('userInput').value = message;
    sendMessage();
}

function showStats() {
    const modal = document.getElementById('statsModal');
    modal.style.display = 'block';
    
    fetch('/api/stats')
        .then(response => response.json())
        .then(data => {
            let statsHtml = `
                <p><strong>Total Messages:</strong> ${data.totalMessages}</p>
                <p><strong>Top Words Used:</strong></p>
                <ul>
            `;
            
            if (data.topWords && data.topWords.length > 0) {
                data.topWords.forEach(word => {
                    statsHtml += `<li>${word.key}: ${word.value} times</li>`;
                });
            } else {
                statsHtml += '<li>No data yet. Start chatting!</li>';
            }
            
            statsHtml += '</ul>';
            document.getElementById('statsContent').innerHTML = statsHtml;
        })
        .catch(error => {
            console.error('Error fetching stats:', error);
            document.getElementById('statsContent').innerHTML = 
                '<p>Unable to load statistics.</p>';
        });
}

function closeStats() {
    document.getElementById('statsModal').style.display = 'none';
}

window.onclick = function(event) {
    const modal = document.getElementById('statsModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}

window.onload = function() {
    document.getElementById('userInput').focus();
}