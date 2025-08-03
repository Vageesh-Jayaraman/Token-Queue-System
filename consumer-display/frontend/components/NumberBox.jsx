import React from 'react';

function NumberBox({ number }) {
  const boxStyle = {
    width: '60px',
    height: '60px',
    backgroundColor: 'black',
    color: 'white',
    fontSize: '24px',
    fontWeight: 'bold',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    borderRadius: '10px',
  };

  return <div style={boxStyle}>{number}</div>;
}

export default NumberBox;
