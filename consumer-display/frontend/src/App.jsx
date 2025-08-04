import React, { useEffect, useState } from 'react';
import DepartmentGroup from '../components/DepartmentGroup';

function App() {
  const [departments, setDepartments] = useState({}); 

  useEffect(() => {
    const eventSource = new EventSource('http://localhost:8082/stream');

    eventSource.onmessage = (event) => {
      console.log(event.data);
      const data = JSON.parse(event.data);
      
      const { event: eventType, departmentId, tokenNo } = data;

      setDepartments((prev) => {
        const currentTokens = prev[departmentId] || [];

        if (eventType === 'TOKEN_GENERATED') {
          return {
            ...prev,
            [departmentId]: [...currentTokens, tokenNo],
          };
        }

        if (eventType === 'TOKEN_SERVED') {
          return {
            ...prev,
            [departmentId]: currentTokens.filter((t) => t !== tokenNo),
          };
        }

        return prev; 
      });
    };

    eventSource.onerror = (err) => {
      console.error('SSE error:', err);
      eventSource.close();
    };

    return () => {
      eventSource.close();
    };
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      {Object.entries(departments).map(([departmentId, tokens]) => (
        <DepartmentGroup
          key={departmentId}
          department={departmentId}
          numbers={tokens}
        />
      ))}
    </div>
  );
}

export default App;
