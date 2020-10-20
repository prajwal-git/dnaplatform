import React from "react";
import SimpleChart from "./../Chart/SimpleChart";
import BubbleChart from "./../Chart/BubbleChart";

const dashboard = () => {
  return (
    <div className="container">
      <div>Dashboard</div>
      <BubbleChart />
      <SimpleChart />
    </div>
  );
};

export default dashboard;

// export default class Example extends PureComponent {
