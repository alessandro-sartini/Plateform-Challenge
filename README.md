# RestFinder 🍽️

## Overview

RestFinder is a comprehensive restaurant discovery and management platform developed collaboratively by [Alessandro Sartini](https://github.com/alessandro-sartini) and [Karlo Kasi](https://github.com/karlo-kasi). The platform combines modern web technologies with powerful backend services to provide users with an intuitive way to discover, explore, and interact with restaurants.

## 🚀 Features

### For Users

- **Advanced Restaurant Search**

  - Filter by location, cuisine type, and categories
  - Real-time map integration with Google Maps
  - Detailed restaurant information including photos, ratings, and reviews

- **User Experience**

  - Intuitive and responsive interface
  - Multi-language support
  - Interactive restaurant cards with dynamic content
  - Seamless map navigation for restaurant locations

- **Personal Account Features**
  - User registration and authentication
  - Favorite restaurants management
  - Review and rating system
  - Personalized restaurant recommendations

### For Administrators

- **Restaurant Management**

  - Complete CRUD operations for restaurant entries
  - Bulk import functionality using Google Places API
  - Category and tag management
  - Image management system

- **Platform Analytics**
  - API usage monitoring
  - User activity tracking
  - Performance metrics
  - Search pattern analysis

## 🛠️ Technical Stack

### Frontend

- **Framework**: React.js with Vite
- **State Management**: Context API
- **UI Components**: Custom components with modern design
- **Maps Integration**: Google Maps API
- **Styling**: CSS Modules and modern CSS features
- **Internationalization**: i18next
- **HTTP Client**: Axios

### Backend

- **Framework**: Spring Boot
- **Security**: Spring Security with JWT
- **Database**: MySQL
- **API Documentation**: Swagger/OpenAPI
- **Image Processing**: Custom image storage service
- **External APIs**: Google Places API integration

## 🏗️ Architecture

The project follows a modern microservices architecture:

```
RestFinder/
├── frontend/                 # React application
│   ├── src/
│   │   ├── components/      # Reusable UI components
│   │   ├── pages/          # Main application pages
│   │   ├── context/        # Global state management
│   │   └── services/       # API integration
│   └── public/             # Static assets
│
├── backend/                 # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── controller/  # REST endpoints
│   │   │   │   ├── service/     # Business logic
│   │   │   │   ├── model/       # Data entities
│   │   │   │   └── config/      # Application configuration
│   │   │   └── resources/       # Application properties
│   │   └── test/                # Unit and integration tests
│   └── pom.xml                  # Dependencies and build configuration
```

## 🌟 Key Technical Features

1. **Robust Authentication System**

   - JWT-based authentication
   - Role-based access control
   - Secure password handling

2. **Advanced Data Management**

   - Efficient database queries
   - Caching mechanisms
   - Transaction management

3. **External Service Integration**

   - Google Places API for restaurant data
   - Google Maps for location visualization
   - Image processing and storage

4. **Performance Optimization**
   - Lazy loading of images
   - Efficient state management
   - API call optimization

## 🚀 Getting Started

### Prerequisites

- Node.js (v16+)
- Java JDK 17+
- MySQL 8+
- Maven 3.8+

### Installation Steps

1. Clone the repository

```bash
git clone https://github.com/[username]/RestFinder.git
```

2. Frontend Setup

```bash
cd frontend
npm install
npm run dev
```

3. Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

## 🤝 Contribution

This project was developed as a team effort by:

- [Alessandro Sartini](https://github.com/alessandro-sartini) - Backend Development, API Integration
- [Karlo Kasi](https://github.com/karlo-kasi) - Frontend Development, UI/UX Design

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🌟 Acknowledgments

- Thanks to Generation Italy for the project opportunity
- Special thanks to our mentors and advisors
- Google Maps and Places API for location services
