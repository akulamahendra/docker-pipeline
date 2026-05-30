let startTime = Date.now();
let timerInterval;
let matchesFound = 0;
let isProcessing = false;

// Start timer
timerInterval = setInterval(updateTimer, 1000);

function updateTimer() {
    const elapsed = Math.floor((Date.now() - startTime) / 1000);
    const minutes = Math.floor(elapsed / 60);
    const seconds = elapsed % 60;
    document.getElementById('timer').textContent = 
        `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

async function flipCard(cardElement) {
    if (isProcessing || cardElement.classList.contains('flipped') || cardElement.classList.contains('matched')) {
        return;
    }
    
    const cardIndex = parseInt(cardElement.dataset.index);
    
    // Flip the card visually
    cardElement.classList.add('flipped');
    
    try {
        const response = await fetch('flip', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `cardIndex=${cardIndex}`
        });
        
        const data = await response.json();
        
        if (!data.success) {
            cardElement.classList.remove('flipped');
            return;
        }
        
        if (data.wait) {
            isProcessing = true;
            
            // Wait to show both cards
            setTimeout(() => {
                if (data.match) {
                    // Mark as matched
                    const allCards = document.querySelectorAll('.card.flipped:not(.matched)');
                    allCards.forEach(card => card.classList.add('matched'));
                    
                    matchesFound = data.matchesFound;
                    document.getElementById('matches').textContent = matchesFound;
                    
                    if (data.gameFinished) {
                        clearInterval(timerInterval);
                        showWinModal(data.time, data.rank);
                    }
                } else {
                    // Flip back unmatched cards
                    const flippedCards = document.querySelectorAll('.card.flipped:not(.matched)');
                    flippedCards.forEach(card => card.classList.remove('flipped'));
                }
                
                isProcessing = false;
            }, 1000);
        }
        
    } catch (error) {
        console.error('Error:', error);
        cardElement.classList.remove('flipped');
    }
}

function showWinModal(time, rank) {
    document.getElementById('finalTime').textContent = time;
    document.getElementById('finalRank').textContent = rank;
    document.getElementById('winModal').classList.add('show');
}
