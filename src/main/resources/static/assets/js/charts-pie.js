const pieConfig = {
  type: 'doughnut',
  data: {
    datasets: [
      {
        data: [33, 33, 33],
        backgroundColor: ['#0694a2', '#1c64f2', '#7e3af2'],
        label: 'Dataset 1',
      },
    ],
    labels: ['Shoes', 'Shirts', 'Bags'],
  },
  options: {
    responsive: true,
    cutout: '80%',
    plugins: {
      legend: {
        display: false,
      },
    },
  },
};

const pieCtx = document.getElementById('pie');
window.myPie = new Chart(pieCtx, pieConfig);
