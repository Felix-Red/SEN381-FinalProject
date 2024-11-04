<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Work Order - Page 1</title>
    <link rel="stylesheet" href="Styling/style.css">
    <link rel="stylesheet" href="Styling/workOrder.css">
</head>
<body>
    <!-- Include Header -->
    <div class="header">
        <h1>Work Order Dashboard</h1>
        <p>Overview of all work orders and assignments.</p>
    </div>

    <!-- Table Content -->
    <div class="content">
        <h2>Work Order</h2>
        <table class="work-order-table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Request ID</th>
                    <th>Request Information</th>
                    <th>Requester Name</th>
                    <th>Technician</th>
                    <th>Assigned Date</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>7</td>
                    <td>Keyboard Problem</td>
                    <td>Saina Naik</td>
                    <td>Sanjay Joshi</td>
                    <td>2021-07-21</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>17</td>
                    <td>Laptop Screen Issue</td>
                    <td>Trupti Smandrekar</td>
                    <td>Raj Naik</td>
                    <td>2021-07-08</td>
                </tr>
                <!-- Add more rows as needed -->
            </tbody>
        </table>
    </div>
</body>
</html>
