// Định dạng số trang hiện tại và tổng số trang
var currentPage = 1;
var totalPages = Math.ceil(${products.size()}) / 10;

// Hàm để tạo nút phân trang
function createPageButton(pageNumber) {
    var button = document.createElement("li");
    button.className = "page-item";
    button.innerHTML = `<a class="page-link" href="#" onclick="changePage(${pageNumber})">${pageNumber}</a>`;
    return button;
}

// Hàm để cập nhật giao diện phân trang
function updatePagination() {
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    var prevButton = createPageButton("&#171;"); // Nút "Previous"
    pagination.appendChild(prevButton);

    for (var i = 1; i <= totalPages; i++) {
        var pageButton = createPageButton(i);
        pagination.appendChild(pageButton);
    }

    var nextButton = createPageButton("&#187;"); // Nút "Next"
    pagination.appendChild(nextButton);
}
function loadProductsByPage(pageNumber) {
    // Sử dụng Ajax để gửi yêu cầu đến máy chủ và lấy danh sách sản phẩm cho trang pageNumber
    // Sử dụng jQuery.ajax hoặc Fetch API để thực hiện yêu cầu Ajax

    // Sau khi nhận được dữ liệu từ máy chủ, cập nhật danh sách sản phẩm trong DOM
    // Ví dụ:
    $.ajax({
        url: "/products/load?page=" + pageNumber,
        method: "GET",
        success: function(data) {
            // Cập nhật danh sách sản phẩm trong DOM với dữ liệu mới
            // Ví dụ: $("#product-list").html(data);
        },
        error: function(error) {
            console.error("Error loading products: " + error);
        }
    });
}

// Hàm để thay đổi trang sản phẩm
function changePage(pageNumber) {
    if (pageNumber === "&#171;") { // Nhấn nút "Previous"
        if (currentPage > 1) {
            currentPage--;
        }
    } else if (pageNumber === "&#187;") { // Nhấn nút "Next"
        if (currentPage < totalPages) {
            currentPage++;
        }
    } else { // Nhấn nút trang cụ thể
        currentPage = pageNumber;
    }

    // Gọi hàm để cập nhật danh sách sản phẩm dựa trên trang hiện tại (currentPage)
     loadProductsByPage(currentPage);

    // Cập nhật giao diện phân trang sau khi thay đổi trang
    updatePagination();
}

// Khởi đầu, cập nhật giao diện phân trang ban đầu
updatePagination();
