document.getElementById('getStudents').addEventListener('submit', function(event) {
  event.preventDefault();

  fetch('/students-list', {
    method: 'GET'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Ошибка получения списка студентов');
    }
    return response.json();
  })
  .then(data => {
    // Обработка полученных данных, например, вывод на страницу
    console.log('Список студентов:', data);
  })
  .catch(error => {
    alert(error.message);
  });
});

// ==============================================================================
document.getElementById('createStudentForm').addEventListener('submit', function(event) {
  event.preventDefault();
  
  let firstName = document.getElementById("firstName").value;
  let lastName = document.getElementById("lastName").value;
  let middleName = document.getElementById("middleName").value;
  let birthdate = document.getElementById("birthdate").value;
  let group = document.getElementById("group").value;
  
  let studentData = {
    firstName: firstName,
    lastName: lastName,
    middleName: middleName,
    birthdate: birthdate,
    group: group
  };
  
  console.log(studentData);

  fetch('/create-student', {
    method: 'POST',
    body: JSON.stringify(studentData)
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Ошибка создания студента');
    }
    alert('Студент успешно создан');
    this.reset(); // Очистка формы после успешного создания студента
  })
  .catch(error => {
    alert(error.message);
  });
});

// ==============================================================================
document.getElementById('deleteStudentForm').addEventListener('submit', function(event) {
  event.preventDefault();
  
  const studentId = document.getElementById('studentId').value;
  fetch(`/delete-student?id=${studentId}`, {
    method: 'DELETE'
  })
  .then(response => {
    if (!response.ok) {
      throw new Error('Ошибка удаления студента');
    }
    alert('Студент успешно удален');
    this.reset(); // Очистка формы после успешного удаления студента
  })
  .catch(error => {
    alert(error.message);
  });
});
