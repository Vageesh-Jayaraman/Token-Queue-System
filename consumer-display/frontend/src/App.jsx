import React from 'react';
import NumberBox from '../components/NumberBox';

function App() {
  const numbers = Array.from({ length: 56 }, (_, i) => i + 1);

  const containerStyle = {
    display: 'flex',
    flexWrap: 'wrap',
    gap: '12px',
    padding: '20px',
  };

  return (
    <div className="App" style={containerStyle}>
      {numbers.map((num) => (
        <NumberBox key={num} number={num} />
      ))}
    </div>
  );
}

export default App;
