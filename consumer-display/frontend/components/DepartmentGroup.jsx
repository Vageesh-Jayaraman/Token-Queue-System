import React from 'react';
import NumberBox from './NumberBox';

function DepartmentGroup({ department, numbers }) {
    

    const containerStyle = {
        display: 'flex',
        flexWrap: 'wrap',
        gap: '12px',
        marginBottom: '24px',
    };

    return (
        <div>
            <h3>Department: {department}</h3>
            <div style={containerStyle}>
                {numbers.map((num) => (
                    <NumberBox key={num} number={num} />
                ))}
            </div>
        </div>
    );
}

export default DepartmentGroup;
