const chatForm = document.getElementById("chatForm");
const userInput = document.getElementById("userInput");
const chatHistory = document.getElementById("chatHistory");

// Menu
const menu = {
  burger: 120,
  pizza: 250,
  fries: 100,
  coke: 50
};

// Knowledge base
const responseDictionary = {
  greeting: "Welcome to our restaurant! 🍽️ How can I help you?",
  
  menu: "Here is our menu:\n🍔 Burger - ₹120\n🍕 Pizza - ₹250\n🍟 Fries - ₹100\n🥤 Coke - ₹50",
  
  order: "What would you like to order?",
  
  reservation: "Sure! Please tell me the date and number of people.",
  
  location: "Choose pickup location: Main Branch / City Mall",
  
  payment: "Payment options: Cash / UPI / Card"
};

// Follow-up flows
const followUpFlows = {
  order: {
    prompt: "Please type item name (burger/pizza/fries/coke):",
    handle: function(input) {
      if (menu[input]) {
        return `✅ ${input} added to your order. Total: ₹${menu[input]}\n` +
               "Would you like anything else? (yes/no)";
      }
      return "Item not found in menu.";
    }
  },

  reservation: {
    prompt: "Enter number of people:",
    handle: function(input) {
      return `✅ Table booked for ${input} people. See you soon!`;
    }
  }
};

let activeFlow = null;

// Event
chatForm.addEventListener("submit", function(e) {
  e.preventDefault();

  let input = userInput.value.toLowerCase();
  appendMessage("You", input);

  let response = getBotResponse(input);
  appendMessage("Bot", response);

  userInput.value = "";
});

// Append
function appendMessage(sender, text) {
  const className = sender === "You" ? "user" : "bot";
  chatHistory.innerHTML += `<p class="${className}"><b>${sender}:</b> ${text}</p>`;
  chatHistory.scrollTop = chatHistory.scrollHeight;
}

// Logic
function getBotResponse(input) {

  // Handle active flow
  if (activeFlow) {
    let flow = followUpFlows[activeFlow];
    activeFlow = null;
    return flow.handle(input);
  }

  if (input.includes("hello") || input.includes("hi")) {
    return responseDictionary.greeting;
  }

  if (input.includes("menu")) {
    return responseDictionary.menu;
  }

  if (input.includes("order")) {
    activeFlow = "order";
    return responseDictionary.order + "\n" + followUpFlows.order.prompt;
  }

  if (input.includes("book") || input.includes("reservation")) {
    activeFlow = "reservation";
    return responseDictionary.reservation + "\n" + followUpFlows.reservation.prompt;
  }

  if (input.includes("location")) {
    return responseDictionary.location;
  }

  if (input.includes("payment")) {
    return responseDictionary.payment;
  }

  if (input.includes("bye")) {
    return "Thank you! Visit again 😊";
  }

  return "Sorry, I didn’t understand. Please choose: menu / order / reservation.";
}