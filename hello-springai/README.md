# Hello Spring AI

A comprehensive demonstration project showcasing Spring AI capabilities including basic chat interactions, function calling, tool calling, and a practical wallet management system with real-time stock data integration.

## 🚀 Features

- **Basic AI Chat**: Simple text-based interactions with OpenAI models
- **System Prompts**: Context-aware conversations with predefined system roles
- **Streaming Responses**: Real-time streaming of AI responses
- **Structured Output**: Entity extraction and structured data responses
- **Prompt Templates**: Dynamic prompt generation with parameter substitution
- **Chat Memory**: Conversation state management with message windowing
- **Tool Calling**: Integration with external APIs (stock data) and database operations
- **Function Calling**: Structured function execution with AI orchestration
- **Wallet Management**: Portfolio tracking with real-time stock valuations

## 🛠️ Technology Stack

- **Java 21**
- **Spring Boot 3.5.3**
- **Spring AI 1.0.0**
- **Spring Data JPA**
- **H2 Database**
- **OpenAI GPT-4o-mini**
- **Twelve Data API** (for stock data)
- **Lombok**

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.6+
- OpenAI API key
- Twelve Data API key (optional, for stock functionality)

## ⚙️ Configuration

### Environment Variables

Set the following environment variables:

```bash
export OPENAI_API_KEY="your-openai-api-key"
export STOCK_API_KEY="your-twelvedata-api-key"  # Optional
```

### Application Properties

The application uses the following default configuration:

```properties
spring.application.name=springai
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o-mini
```

## 🏃‍♂️ Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hello-springai
   ```

2. **Set environment variables**
   ```bash
   export OPENAI_API_KEY="your-openai-api-key"
   export STOCK_API_KEY="your-twelvedata-api-key"
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application will start on `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console` (if enabled)

## 📚 API Endpoints

### Basic AI Interactions

#### Health Check
```http
GET /
```
Returns a simple "Hello, World!" message to verify the server is running.

#### Basic Chat
```http
GET /basic?message=Tell me a joke
```
Basic text-based interaction with the AI model.

#### System Prompt
```http
GET /system
```
Demonstrates system prompts with a Seinfeld-themed chatbot.

#### Streaming Response
```http
GET /stream?message=Write a short story
```
Returns AI responses in real-time streaming format.

#### Structured Output
```http
GET /career
```
Returns structured data (top 10 careers by salary) using entity extraction.

#### Prompt Templates
```http
GET /promptwhois?name=Albert Einstein
```
Uses parameterized prompt templates for dynamic content generation.

#### Movie-themed Chat
```http
GET /promptmessages?movie=The Matrix
```
Combines system prompts with user messages for themed responses.

#### Chat Memory
```http
GET /chatmemory?message=My name is John
```
Maintains conversation context with a 2-message window.

### Wallet Management

#### Calculate Current Wallet Value
```http
GET /wallet/with-tools
```
Calculates the current value of your investment portfolio using real-time stock prices.

**Sample Response:**
```
Here are the current stock prices and the total value of your wallet based on the latest prices:
- **AAPL**: 100 shares at $209.94 each = $20,994.00
- **AMZN**: 300 shares at $223.48 each = $67,044.00
- **META**: 300 shares at $718.64 each = $215,592.00
- **MSFT**: 400 shares at $497.83 each = $199,132.00
- **NVDA**: 200 shares at $158.24 each = $31,648.00

### Total Wallet Value: $534,410.00
```

#### Historical Analysis
```http
GET /wallet/highest-day/{days}
```
Analyzes historical data to find the day with the highest portfolio value over the specified period.

## 🏗️ Project Structure

```
src/main/java/com/aparna/java/hello_springai/
├── HelloSpringaiApplication.java          # Main application class
├── HelloSpringAIController.java           # Basic AI interaction endpoints
├── controllers/
│   └── WalletController.java              # Wallet management endpoints
├── functioncalling/
│   ├── stock/                             # Stock data models
│   │   ├── DailyShareQuote.java
│   │   ├── DailyStockData.java
│   │   ├── Stock.java
│   │   ├── StockData.java
│   │   ├── StockRequest.java
│   │   ├── StockResponse.java
│   │   └── StockService.java
│   └── wallet/                            # Wallet data models
│       ├── Share.java
│       ├── Wallet.java
│       ├── WalletRepository.java
│       ├── WalletResponse.java
│       └── WalletService.java
└── toolcalling/
    ├── StockTools.java                    # Stock API integration tools
    └── WalletTools.java                   # Wallet database tools
```

## 💾 Database Schema

The application uses an H2 in-memory database with the following structure:

### Share Table
```sql
CREATE TABLE share (
    id BIGINT PRIMARY KEY,
    company VARCHAR(255),
    quantity INTEGER
);
```

### Sample Data
The application comes pre-loaded with sample portfolio data:
- AAPL: 100 shares
- AMZN: 300 shares
- META: 300 shares
- MSFT: 400 shares
- NVDA: 200 shares

## 🔧 Key Components

### Tool Calling
- **StockTools**: Integrates with Twelve Data API for real-time and historical stock prices
- **WalletTools**: Manages portfolio data through JPA repository

### Function Calling
- Demonstrates structured function execution with AI orchestration
- Combines multiple data sources for comprehensive analysis

### Chat Memory
- Implements conversation state management
- Uses message windowing for context retention

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

## 📝 Examples

### Basic Usage

1. **Start the application**
   ```bash
   mvn spring-boot:run
   ```

2. **Test basic chat**
   ```bash
   curl "http://localhost:8080/basic?message=Hello%20world"
   ```

3. **Check wallet value**
   ```bash
   curl "http://localhost:8080/wallet/with-tools"
   ```

4. **Analyze historical performance**
   ```bash
   curl "http://localhost:8080/wallet/highest-day/30"
   ```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Spring AI team for the excellent framework
- OpenAI for providing the AI models
- Twelve Data for stock market data API

## 📞 Support

For questions or issues, please open an issue on the GitHub repository. 