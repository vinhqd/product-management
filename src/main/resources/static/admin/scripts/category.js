$(document).ready(function() {
   $("#category").addClass("active");

   $("table #btnEdit").on("click", function(e) {
      e.preventDefault();

      let href = $(this).attr("href");

      $.get(href, function(category, status) {
         $("#nameEdit").val(category.name);
         $("#codeEdit").val(category.code);
         $("#idEdit").val(category.id);
      });

      $("#editModal").modal();
   });

   $(' table #btnDeleteRecord').on('click', function(e){
      e.preventDefault();
      var href = $(this).attr('href');
      $('#confirmDeteleButton').attr('href', href);
      $('#deleteModal').modal();

   });
});