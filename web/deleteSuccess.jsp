<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Service Request Success</title>
    <style>
        /* Reset some default styles */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
        }

        /* Container for the success card */
        .success-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f0f8ff; /* Light background for better visuals */
        }

        /* Card style */
        .success-card {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 40px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }

        /* Success icon style */
        .icon-container {
            margin-bottom: 20px;
        }

        .success-icon {
            font-size: 60px;
            color: #28a745; /* Green color for success */
        }

        /* Main title */
        .success-card h1 {
            color: #333;
            font-size: 28px;
            margin-bottom: 15px;
        }

        /* Description text */
        .success-card p {
            color: #555;
            font-size: 16px;
            margin-bottom: 25px;
        }

        /* Primary button style */
        .btn-primary {
            display: inline-block;
            background-color: #007bff;
            color: #ffffff;
            padding: 12px 25px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3; /* Darker shade of blue for hover effect */
        }
    </style>
</head>
<body>
    <div class="success-container">
        <div class="success-card">
            <div class="icon-container">
                <span class="success-icon">&#10004;</span> <!-- Check mark icon -->
            </div>
            <h1>Contract terminated Successfully!</h1>
            <p>Your contract has been successfully terminated from the system.</p>
            <a href="clientDashboard.jsp" class="btn-primary">Return to Dashboard</a>
        </div>
    </div>
</body>
</html>
