document.addEventListener('DOMContentLoaded', function () {
  var calendarEl = document.getElementById('calendar');

  if (!calendarEl) {
    console.error("No se encontró el elemento con id 'calendar'");
    return;
  }

  var calendar = new FullCalendar.Calendar(calendarEl, {
    initialView: 'dayGridMonth',
    locale: 'es',
    selectable: true,
    editable: true, // Habilita mover y redimensionar eventos
    eventStartEditable: true,
    eventDurationEditable: true,
    events: '/citas/api/citas', // Asegúrate de que este endpoint exista

    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,listMonth'
    },

    eventDrop: function(info) {
      actualizarCita(info.event);
    },

    eventResize: function(info) {
      actualizarCita(info.event);
    },

    eventClick: function(info) {
      // Redirige al formulario de edición
      window.location.href = '/citas/editar/' + info.event.id;
    },

    dateClick: function(info) {
      // Redirige al formulario de nueva cita con la fecha seleccionada
      window.location.href = '/citas/nueva?fecha=' + info.dateStr;
    }
  });

  calendar.render();

  // Función para actualizar la cita en el backend
  function actualizarCita(event) {
    fetch('/citas/api/citas/' + event.id, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        inicio: event.start.toISOString(),
        fin: event.end ? event.end.toISOString() : event.start.toISOString()
      })
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Error al actualizar la cita');
      }
      return response.json();
    })
    .then(data => {
      console.log('Cita actualizada correctamente');
    })
    .catch(error => {
      console.error('No se pudo actualizar la cita:', error);
    });
  }
});
