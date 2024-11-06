<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
        /* General reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* Body styling */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f7fc;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        /* Main container for the form */
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #4a90e2;
        }

        /* Label styling */
        label {
            font-size: 1rem;
            margin-bottom: 8px;
            display: block;
            font-weight: bold;
        }

        /* Input fields styling */
        select, textarea, input[type="submit"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 6px;
            border: 1px solid #ccc;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        select:focus, textarea:focus {
            border-color: #4a90e2;
            outline: none;
        }

        textarea {
            resize: vertical;
            height: 120px;
        }

        /* Submit button styling */
        input[type="submit"] {
            background-color: #4a90e2;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #357ab7;
        }

        /* Error message styling */
        .error-message {
            color: red;
            text-align: center;
            font-size: 0.9rem;
            margin-top: 10px;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .form-container {
                padding: 20px;
                width: 90%;
            }

            h2 {
                font-size: 1.5rem;
            }

            select, textarea, input[type="submit"] {
                font-size: 0.9rem;
            }
        }
    </style>
    </head>
    <body>
        <h2>Request a Service</h2>

    <form action="ServiceRequestServlet" method="post">
        <label for="serviceType">Service Type:</label>
        <select id="serviceType" name="serviceType" required>
            <option value="Standard">Standard</option>
            <option value="Premium">Premium</option>
        </select><br>

        <label for="description">Description:</label><br>
        <textarea id="description" name="description" rows="4" cols="50" required></textarea><br>

        <input type="submit" value="Submit Request">
    </form>
    </body>
</html>
