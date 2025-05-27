# Railway Ticket Refund Calculator

A **Java Spring Boot** application to calculate estimated refund amounts for Indian Railway ticket cancellations based on IRCTC’s official cancellation policies.

---

## 🌟 Features

- **General Cancellation Enquiry:**  
  Input ticket amount, journey date or hours left before journey, ticket class (Sleeper/AC 1st, 2nd, 3rd, 3E), and ticket status (Confirmed, RAC, Waiting) to calculate cancellation charges and refund amount.

- **PNR-Based Enquiry:**  
  Provide a PNR number to automatically fetch journey details using RapidAPI’s Indian Railway PNR Status API, then calculate exact refund based on ticket fare and cancellation time.

- **Cancellation Charge Details:**  
  Detailed breakdown of charges and deductions based on ticket class and cancellation timing, matching IRCTC’s official rules.

- **Personalized Suggestions:**  
  Suggests the optimal time or date before journey to cancel for maximizing refund benefits.

- **Flexible Input Options:**  
  Allows user to enter cancellation timing as either hours left before journey or select the journey date to compute deductions.

---

## 📋 Getting Started

### Prerequisites

- Java 11 or later  
- Maven 3.x  
- Internet connection (for PNR API integration)  
- API Key for [RapidAPI Indian Railway PNR Status](https://rapidapi.com/)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/railway-refund-calculator.git
   cd railway-refund-calculator
   ```

2. Configure your RapidAPI key in `application.properties` or environment variables:
   ```ini
   rapidapi.key=YOUR_RAPIDAPI_KEY
   rapidapi.host=irctc1.p.rapidapi.com
   ```
3. Build and run the application:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

---

## 🛠️ Usage

### 1. General Cancellation Enquiry

Send a POST request to `/api/cancellation/general` with JSON body:

```json
{
  "ticketAmount": 1500,
  "class": "SL",
  "ticketType": "Confirmed",
  "timeLeftInHours": 48
}
```

### 2. Cancellation Enquiry with PNR

Send a GET request to `/api/cancellation/pnr/{pnrNumber}`

Example: `/api/cancellation/pnr/8524877966`

Response includes:

- Passenger details
- Journey date/time
- Class and ticket status
- Fare amount (user input if not available)
- Cancellation charges and refund estimate
- Personalized suggestions for optimal cancellation timing

---

## 🧩 How It Works

1. **Fetch PNR details:**  
The app calls RapidAPI’s Indian Railway PNR Status API using the PNR number.

2. **Parse ticket details:**  
   Extracts journey class, date, passenger count, ticket status, and fare.

3. **Calculate charges:**  
   Applies IRCTC cancellation policies based on the class and time left before journey.

4. **Provide suggestions:**  
   Calculates when cancelling yields the maximum refund, based on official deduction slabs.

## ⚙️ Configuration

| Property          | Description                             | Example               |
|-------------------|---------------------------------------|-----------------------|
| `rapidapi.key`    | API key for RapidAPI                   | `abcdef123456`        |
| `rapidapi.host`   | RapidAPI host for railway PNR API     | `irctc1.p.rapidapi.com` |
| `server.port`     | Application server port (default 8080)| `8080`                |

---

## 🛡️ Security

- API keys should **never** be hardcoded in source code or committed to version control.  
- Use environment variables or Spring Boot external config files to securely manage sensitive credentials.

---

## 📚 Technologies Used

- Java 11  
- Spring Boot  
- Spring Web  
- RestTemplate / WebClient (for API calls)  
- Maven  
- JSON Parsing (Jackson/Gson)  

---

## 🤝 Contributing

Contributions are welcome! Feel free to submit issues or pull requests for improvements or new features.

---

## 📄 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Ankit Panigrahi**  
Computer Science Engineer | Software Developer | Open Source Enthusiast  
[GitHub](https://github.com/PanigrahiAnkit) | [LinkedIn](https://linkedin.com/in/panigrahi0702)

---

## 🙏 Acknowledgements

- IRCTC Official Cancellation Policies  
- RapidAPI Indian Railway PNR Status API  
- Open-source Java and Spring Boot communities
