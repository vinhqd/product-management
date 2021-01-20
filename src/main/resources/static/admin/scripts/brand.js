$(document).ready(function() {
   $("#products").addClass("menu-open");
   $("#productsMenu").addClass("active");
   $("#brand").addClass("active");

   $("#btnAdd").on('click', function (e) {
      e.preventDefault();
      $('#editModal #modalLabel').text("Thêm mới thương hiệu")
      $('#editModal form input[type=submit]').val("Thêm mới")
      $("#id").val("");
      $("#name").val("");
      $("#code").val("");
      $("#editModal").modal();
   });

   $("#btnEdit").on('click', function (e) {
      e.preventDefault();
      $('#editModal #modalLabel').text("Sửa thương hiệu")
      $('#editModal form input[type=submit]').val("Sửa")
      var href = $(this).attr('href');
      $.get(href, function (brand, status) {
         $("#id").val(brand.id);
         $("#name").val(brand.name);
         $("#code").val(brand.code);
      });
      $("#editModal").modal();
   });

   $("#btnDeleteRecord").on('click', function (e){
     e.preventDefault();
     let href = $(this).attr('href');
     $('#confirmDeteleButton').attr('href', href);
     $('#deleteModal').modal();
   });
});